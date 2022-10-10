package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.trifood.dto.FoodDto;
import com.tripath.trifood.api.trifood.dto.PaymentManagerDto;
import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.api.trifood.response.FoodResponse;
import com.tripath.trifood.api.trifood.response.StudentPaymentResponse;
import com.tripath.trifood.api.trifood.services.service.PaymentManagerService;
import com.tripath.trifood.entities.Food;
import com.tripath.trifood.entities.StudentPayment;
import com.tripath.trifood.api.trifood.dto.StudentPaymentDto;
import com.tripath.trifood.repositories.trifood.GroupScheduleRespository;
import com.tripath.trifood.repositories.trifood.StudentPaymentRepository;
import com.tripath.trifood.api.trifood.services.service.StudentPaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentPaymentServiceImpl implements StudentPaymentService {

    @Autowired
    private StudentPaymentRepository studentPaymentRepo;

    @Autowired
    private GroupScheduleRespository scheduleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentPaymentDto createStudentPayment(StudentPaymentDto studentPaymentDto) {
        StudentPayment studentPayment = this.modelMapper.map(studentPaymentDto, StudentPayment.class);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String startDate = dateFormat.format(studentPayment.getPayStartDate());
        String endDate = dateFormat.format(studentPayment.getPayEndDate());

        Integer groupId = scheduleRepo.findStudentGroupId(studentPayment.getStudent().getId());
        Integer schedulePayment = scheduleRepo.getTotalPayment(startDate, endDate, groupId);
        Integer minusPayment = studentPaymentRepo.getMonthlyMinusPayment(startDate, endDate, studentPayment.getStudent().getId());

        studentPayment.setTotalMoney(schedulePayment-minusPayment);

        StudentPayment newEgroup = this.studentPaymentRepo.save(studentPayment);
        return this.modelMapper.map(newEgroup, StudentPaymentDto.class);
    }

    @Override
    public StudentPaymentDto updateStudentPayment(StudentPaymentDto studentPaymentDto, Long studentPaymentId) {
        StudentPayment studentPayment = this.studentPaymentRepo.findById(studentPaymentId).orElseThrow(()-> new ResourceNotFoundException("StudentPayment", "studentPaymentId", studentPaymentId));
        studentPayment.setIsPaid(studentPaymentDto.getIsPaid());
        studentPayment.setPayDate(studentPaymentDto.getPayDate());
        studentPayment.setTotalMoney(studentPaymentDto.getTotalMoney());
        studentPayment.setStudent(studentPaymentDto.getStudent());
        studentPayment.setPayStartDate(studentPaymentDto.getPayStartDate());
        studentPayment.setPayEndDate(studentPaymentDto.getPayEndDate());

        StudentPayment updatedStudentPayment = this.studentPaymentRepo.save(studentPayment);
        return this.modelMapper.map(updatedStudentPayment, StudentPaymentDto.class);
    }

    @Override
    public void deleteStudentPayment(Long studentPaymentId) {
        StudentPayment studentPayment = this.studentPaymentRepo.findById(studentPaymentId).orElseThrow(()-> new ResourceNotFoundException("StudentPayment", "studentPaymentId", studentPaymentId));
        this.studentPaymentRepo.delete(studentPayment);
    }

    @Override
    public StudentPaymentResponse getAllStudentPayment(String startDate, String endDate, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<PaymentManagerService> pageStudentPayment = this.studentPaymentRepo.getAllPayment(startDate, endDate, p);
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
    public StudentPaymentResponse sortPayment(String startDate, String endDate, String classLevel, String classGrade, String className, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<PaymentManagerService> pageStudentPayment = this.studentPaymentRepo.sortPayment(startDate, endDate, classLevel, classGrade, className, p);
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
    public StudentPaymentResponse searchPaymentByStudentName(String startDate, String endDate, String studentName, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<PaymentManagerService> pageStudentPayment = this.studentPaymentRepo.searchPaymentByStudentName(startDate, endDate, studentName, p);
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

    @Override
    public Integer getMonthlyPayment(String startDate, String endDate, Integer studentId) {
        Integer groupId = this.scheduleRepo.findStudentGroupId(studentId);
        Integer schedulePayment = this.scheduleRepo.getTotalPayment(startDate, endDate, groupId);
        Integer minusPayment = this.studentPaymentRepo.getMonthlyMinusPayment(startDate, endDate, studentId);

        return schedulePayment - minusPayment;
    }

    @Override
    public Integer getStudentTotalMeal(String startDate, String endDate, Integer studentId) {
        try {
            Integer groupId = this.scheduleRepo.findStudentGroupId(studentId);
            Integer groupScheduleId = this.scheduleRepo.findStudentGroupScheduleId(groupId);
            Integer totalGsMeal = this.studentPaymentRepo.countGroupScheduleMeal(startDate, endDate,groupScheduleId);
            Integer totalSMeal  = this.studentPaymentRepo.countStudentDeleteMeal(studentId);
            return totalGsMeal - totalSMeal;
        }
        catch (ResourceNotFoundException exception){
            throw new ResourceNotFoundException("Amount", "Date", studentId, startDate, endDate);
        }

    }
}
