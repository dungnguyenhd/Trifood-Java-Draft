package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.api.trifood.services.service.PaymentManagerService;
import com.tripath.trifood.entities.StudentPayment;
import com.tripath.trifood.api.trifood.dto.StudentPaymentDto;
import com.tripath.trifood.repositories.trifood.GroupScheduleRespository;
import com.tripath.trifood.repositories.trifood.StudentPaymentRepository;
import com.tripath.trifood.api.trifood.services.service.StudentPaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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
    public List<PaymentManagerService> getAllStudentPayment(String startDate, String endDate) {
        List<PaymentManagerService> pageStudentPayment = this.studentPaymentRepo.getAllPayment(startDate, endDate);
        return pageStudentPayment;
    }

    @Override
    public List<PaymentManagerService> sortPayment(String startDate, String endDate, String classLevel, String classGrade, String className) {
        List<PaymentManagerService> pageStudentPayment = this.studentPaymentRepo.sortPayment(startDate, endDate, classLevel, classGrade, className);
        return pageStudentPayment;
    }

    @Override
    public List<PaymentManagerService> searchPaymentByStudentName(String startDate, String endDate, String studentName) {
        List<PaymentManagerService> pageStudentPayment = this.studentPaymentRepo.searchPaymentByStudentName(startDate, endDate, studentName);
        return pageStudentPayment;
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
}
