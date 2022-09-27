package com.tripath.trifood.services.service;

import com.tripath.trifood.payloads.Dto.StudentPaymentDto;
import com.tripath.trifood.payloads.response.StudentPaymentResponse;
import org.springframework.stereotype.Service;

@Service
public interface StudentPaymentService {
    StudentPaymentDto createStudentPayment(StudentPaymentDto studentPaymentDto);

    //update studentPayment
    StudentPaymentDto updateStudentPayment(StudentPaymentDto studentPaymentDto, Integer studentPaymentId);

    //delete studentPayment
    void deleteStudentPayment(Integer studentPaymentId);

    //get all studentPayment
    StudentPaymentResponse getAllStudentPayment(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get single  studentPayment
    StudentPaymentDto getStudentPaymentById(Integer studentPaymentId);
}
