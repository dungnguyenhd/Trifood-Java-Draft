package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.student.repository.StudentRepository;
import com.tripath.trifood.api.trifood.dto.PaymentManagerDto;
import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.api.trifood.response.StudentPaymentResponse;
import com.tripath.trifood.api.trifood.services.service.PaymentManagerService;
import com.tripath.trifood.entities.StudentPayment;
import com.tripath.trifood.api.trifood.dto.StudentPaymentDto;
import com.tripath.trifood.repositories.trifood.*;
import com.tripath.trifood.api.trifood.services.service.StudentPaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class StudentPaymentServiceImpl implements StudentPaymentService {
    @Autowired
    private StudentPaymentRepo studentPaymentRepo;
    @Autowired
    StudentOrderRepo orderRepo;
    @Autowired
    StudentRepository studentRepo;
    @Autowired
    private WeekScheduleRepo weekRepo;
    @Autowired
    private EWeeklyScheduleRepo weeklyRepo;
    @Autowired
    private EGroupRepo eGroupRepo;
    @Autowired
    private MealRepo mealRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentPayment createStudentPayment(Long studentId, Integer weekMonth, Integer weekYear) {
        StudentPayment studentPayment = new StudentPayment();

        // find Monthly Payment
        AtomicReference<Integer> scheduleMonthlyPayment = new AtomicReference<>(0);
        AtomicReference<Integer> orderMinusPayment = new AtomicReference<>(0);

        Long groupId = eGroupRepo.findByStudent(studentId);
        List<Long> listWeekId = weekRepo.findWeekIdByMonth(weekMonth, weekYear, groupId);

        listWeekId.forEach((wId) -> {
            Long wlId = weeklyRepo.findAllByWeekId(wId);
            Integer value = studentPaymentRepo.countWeeklySchedulePayment(wlId);
            scheduleMonthlyPayment.set(scheduleMonthlyPayment.get() + value);
        });

        List<Long> mealId = orderRepo.getAllDeleteMeal(weekMonth, weekYear);

        mealId.forEach((mId) -> {
            Integer value = studentPaymentRepo.countOrderMinusPayment(mId);
            orderMinusPayment.set(orderMinusPayment.get() + value);
        });

        Integer totalMoney = scheduleMonthlyPayment.get() - orderMinusPayment.get();

        // find Monthly Payment

        // find food Amount
            AtomicReference<Long> totalAmount = new AtomicReference<>(0L);

            listWeekId.forEach((wId) -> {
                Long wlId = weeklyRepo.findAllByWeekId(wId);
                Long value = studentPaymentRepo.countMonthlyTotalFood(wlId);
                totalAmount.set(totalAmount.get() + value);
            });

            AtomicReference<Long> deleteAmount = new AtomicReference<>(0L);

            List<Integer> listWNumber = weekRepo.findAllWeekNumberByMonthAndEGroupId(weekMonth, weekYear, groupId);
            listWNumber.forEach((wNb) ->{
                List<Long> listDeleteId = orderRepo.getAllDeleteMealByWeekNumber(wNb, weekYear);
                listDeleteId.forEach((mId) -> {
                    deleteAmount.set(deleteAmount.get()+ 1);
                });
            });

        // find food Amount

        studentPayment.setStudent(studentRepo.findBysId(studentId));
        studentPayment.setTotalMoney(totalMoney);
        studentPayment.setIsPaid(false);
        studentPayment.setPayMonth(weekMonth);
        studentPayment.setFoodAmount(totalAmount.get()-deleteAmount.get());

        StudentPayment newPayment = this.studentPaymentRepo.save(studentPayment);
        return newPayment;
    }

    @Override
    public StudentPaymentDto updateStudentPayment(Long studentPaymentId) {
        StudentPayment studentPayment = this.studentPaymentRepo.findById(studentPaymentId).orElseThrow(()-> new ResourceNotFoundException("StudentPayment", "studentPaymentId", studentPaymentId));
        studentPayment.setIsPaid(studentPayment.getIsPaid());

        StudentPayment updatedStudentPayment = this.studentPaymentRepo.save(studentPayment);
        return this.modelMapper.map(updatedStudentPayment, StudentPaymentDto.class);
    }

    @Override
    public void deleteStudentPayment(Long studentPaymentId) {
        StudentPayment studentPayment = this.studentPaymentRepo.findById(studentPaymentId).orElseThrow(()-> new ResourceNotFoundException("StudentPayment", "studentPaymentId", studentPaymentId));
        this.studentPaymentRepo.delete(studentPayment);
    }

    @Override
    public StudentPaymentResponse getAllStudentPayment(Integer payMonth, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<PaymentManagerService> pageStudentPayment = this.studentPaymentRepo.getAllPayment(payMonth, p);
        List<PaymentManagerService> allPayment = pageStudentPayment.getContent();
        List<PaymentManagerDto> paymentDto = allPayment.stream().map((payment) -> this.modelMapper.map(payment, PaymentManagerDto.class)).collect(Collectors.toList());

        StudentPaymentResponse paymentResponse = new StudentPaymentResponse();
        paymentResponse.setContent(paymentDto);
        paymentResponse.setPageNumber(pageStudentPayment.getNumber());
        paymentResponse.setTotalElements(pageStudentPayment.getTotalElements());
        paymentResponse.setTotalPages(pageStudentPayment.getTotalPages());
        paymentResponse.setLastPage(pageStudentPayment.isLast());

        return paymentResponse;
    }

    @Override
    public StudentPaymentResponse sortPayment(Integer payMonth, String classLevel, String classGrade, String className, String studentName, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<PaymentManagerService> pageStudentPayment = this.studentPaymentRepo.sortPayment(payMonth, classLevel, classGrade, className, studentName ,p);
        List<PaymentManagerService> allPayment = pageStudentPayment.getContent();
        List<PaymentManagerDto> paymentDto = allPayment.stream().map((payment) -> this.modelMapper.map(payment, PaymentManagerDto.class)).collect(Collectors.toList());

        StudentPaymentResponse paymentResponse = new StudentPaymentResponse();
        paymentResponse.setContent(paymentDto);
        paymentResponse.setPageNumber(pageStudentPayment.getNumber());
        paymentResponse.setTotalElements(pageStudentPayment.getTotalElements());
        paymentResponse.setTotalPages(pageStudentPayment.getTotalPages());
        paymentResponse.setLastPage(pageStudentPayment.isLast());

        return paymentResponse;
    }

    @Override
    public StudentPaymentDto getStudentPaymentById(Long studentPaymentId) {
        StudentPayment studentPayment = this.studentPaymentRepo.findById(studentPaymentId).orElseThrow(()-> new ResourceNotFoundException("StudentPayment", "studentPaymentId", studentPaymentId));
        return this.modelMapper.map(studentPayment, StudentPaymentDto.class);
    }


    @Scheduled(cron = "0 15 10 15 * ?")
    public void createPaymentEveryMonth(){
        List<Long> listStu = studentRepo.findAllStudentId();
        Calendar calendar = Calendar.getInstance(Locale.CHINESE);
        listStu.forEach((sId) -> {
            createStudentPayment(sId, calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        });
    }
}
