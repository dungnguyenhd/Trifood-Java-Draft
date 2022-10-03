package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.dto.StudentPaymentDto;
import com.tripath.trifood.api.trifood.response.StudentPaymentResponse;
import org.springframework.stereotype.Service;

@Service
public interface StudentPaymentService {
    StudentPaymentDto createStudentPayment(StudentPaymentDto studentPaymentDto);

    StudentPaymentDto updateStudentPayment(StudentPaymentDto studentPaymentDto, Long studentPaymentId);

    void deleteStudentPayment(Long studentPaymentId);

    StudentPaymentResponse getAllStudentPayment(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    StudentPaymentDto getStudentPaymentById(Long studentPaymentId);

    Integer getMonthlyPayment(String startDate, String endDate, Integer studentId);
}
