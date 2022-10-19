package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.dto.StudentPaymentDto;
import com.tripath.trifood.api.trifood.response.StudentPaymentResponse;
import com.tripath.trifood.entities.StudentPayment;
import org.springframework.stereotype.Service;

@Service
public interface StudentPaymentService {
    StudentPayment createStudentPayment(Long studentId, Integer weekMonth, Integer weekYear);

    StudentPaymentDto updateStudentPayment(Long studentPaymentId);

    void deleteStudentPayment(Long studentPaymentId);

    StudentPaymentResponse getAllStudentPayment(Integer payMonth, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    StudentPaymentResponse sortPayment(Integer payMonth, String classLevel, String classGrade, String className, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    StudentPaymentDto getStudentPaymentById(Long studentPaymentId);

    Integer getStudentTotalMeal(Integer payMonth, Integer studentId);
}
