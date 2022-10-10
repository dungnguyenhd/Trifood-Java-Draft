package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.dto.StudentPaymentDto;
import com.tripath.trifood.api.trifood.response.StudentPaymentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface StudentPaymentService {
    StudentPaymentDto createStudentPayment(StudentPaymentDto studentPaymentDto);

    StudentPaymentDto updateStudentPayment(StudentPaymentDto studentPaymentDto, Long studentPaymentId);

    void deleteStudentPayment(Long studentPaymentId);

    StudentPaymentResponse getAllStudentPayment(String startDate, String endDate, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    StudentPaymentResponse sortPayment(String startDate, String endDate, String classLevel, String classGrade, String className, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    StudentPaymentResponse searchPaymentByStudentName(String startDate, String endDate, String studentName, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    StudentPaymentDto getStudentPaymentById(Long studentPaymentId);

    Integer getMonthlyPayment(String startDate, String endDate, Integer studentId);

    Integer getStudentTotalMeal(String startDate, String endDate, Integer studentId);
}
