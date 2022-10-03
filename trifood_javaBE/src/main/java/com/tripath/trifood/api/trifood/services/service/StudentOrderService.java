package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.dto.StudentOrderDto;
import com.tripath.trifood.api.trifood.response.StudentOrderResponse;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface StudentOrderService {

    StudentOrderDto createStudentOrder(StudentOrderDto studentOrderDto);

    StudentOrderDto updateStudentOrder(StudentOrderDto studentOrderDto, Long studentOrderId);

    void deleteStudentOrder(Long studentOrderId);

    StudentOrderResponse getAllStudentOrder(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    StudentOrderDto getStudentOrderById(Long studentOrderId);

    Integer getStudentGroup(Integer studentId);

    public Integer getTotalMinusPayment(String meal_name, Integer groupId, String scheduleDate);
}
