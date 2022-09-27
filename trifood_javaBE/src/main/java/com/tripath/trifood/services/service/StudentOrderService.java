package com.tripath.trifood.services.service;

import com.tripath.trifood.payloads.Dto.StudentOrderDto;
import com.tripath.trifood.payloads.response.StudentOrderResponse;
import org.springframework.stereotype.Service;

@Service
public interface StudentOrderService {
    //create studentOrder
    StudentOrderDto createStudentOrder(StudentOrderDto studentOrderDto);

    //update studentOrder
    StudentOrderDto updateStudentOrder(StudentOrderDto studentOrderDto, Long studentOrderId);

    //delete studentOrder
    void deleteStudentOrder(Long studentOrderId);

    //get all studentOrder
    StudentOrderResponse getAllStudentOrder(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get single  studentOrder
    StudentOrderDto getStudentOrderById(Long studentOrderId);

}
