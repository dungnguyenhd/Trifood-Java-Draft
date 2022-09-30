package com.tripath.trifood.services.service;

import com.tripath.trifood.payloads.Dto.StudentPaymentDto;
import com.tripath.trifood.payloads.response.StudentPaymentResponse;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    Integer getMonthlyPayment(Date startDate, Date endDate);
}
