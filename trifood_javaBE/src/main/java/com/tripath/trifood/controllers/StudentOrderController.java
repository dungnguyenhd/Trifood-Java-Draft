package com.tripath.trifood.controllers;

import com.tripath.trifood.payloads.Dto.StudentOrderDto;
import com.tripath.trifood.payloads.response.ApiResponse;
import com.tripath.trifood.payloads.response.StudentOrderResponse;
import com.tripath.trifood.services.service.StudentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/studentOrder")
public class StudentOrderController {

    @Autowired
    private StudentOrderService studentOrderService;

    @PostMapping("")
    public ResponseEntity<StudentOrderDto> createStudentOrder(@RequestBody StudentOrderDto studentOrderDto){
        StudentOrderDto createStudentOrder = this.studentOrderService.createStudentOrder(studentOrderDto);
        return new ResponseEntity<>(createStudentOrder, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<StudentOrderResponse> getAllStudentOrders(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "orderId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        StudentOrderResponse studentOrderResponse = this.studentOrderService.getAllStudentOrder(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(studentOrderResponse, HttpStatus.OK) ;
    }

    @GetMapping("/{studentOrderId}")
    public ResponseEntity<StudentOrderDto> getStudentOrderById(@PathVariable Long studentOrderId){
        StudentOrderDto studentOrderDto = this.studentOrderService.getStudentOrderById(studentOrderId);
        return new ResponseEntity<>(studentOrderDto, HttpStatus.OK);
    }

    @DeleteMapping("/{studentOrderId}")
    public ApiResponse deteleStudentOrder(@PathVariable Long studentOrderId){
        this.studentOrderService.deleteStudentOrder(studentOrderId);
        return new ApiResponse("Delete successful",true);
    }

    @PutMapping("/{studentOrderId}")
    public ResponseEntity<StudentOrderDto> updateStudentOrder(@RequestBody StudentOrderDto studentOrderDto, @PathVariable Long studentOrderId){
        StudentOrderDto updatedStudentOrder = this.studentOrderService.updateStudentOrder(studentOrderDto, studentOrderId);
        return new ResponseEntity<>(updatedStudentOrder, HttpStatus.OK);
    }
}
