package com.tripath.trifood.api.trifood.controllers;

import com.tripath.trifood.api.trifood.dto.StudentPaymentDto;
import com.tripath.trifood.api.trifood.response.ApiResponse;
import com.tripath.trifood.api.trifood.response.StudentPaymentResponse;
import com.tripath.trifood.api.trifood.services.service.StudentPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/studentPayment")
public class StudentPaymentController {

    @Autowired
    private StudentPaymentService studentPaymentService;

    @PostMapping("")
    public ResponseEntity<StudentPaymentDto> createStudentPayment(@RequestBody StudentPaymentDto studentPaymentDto){
        StudentPaymentDto createStudentPayment = this.studentPaymentService.createStudentPayment(studentPaymentDto);
        return new ResponseEntity<>(createStudentPayment, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<StudentPaymentResponse> getAllStudentPayments(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "paymentId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        StudentPaymentResponse studentPaymentResponse = this.studentPaymentService.getAllStudentPayment(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(studentPaymentResponse, HttpStatus.OK) ;
    }

    @GetMapping("/{studentPaymentId}")
    public ResponseEntity<StudentPaymentDto> getStudentPaymentById(@PathVariable Long studentPaymentId){
        StudentPaymentDto studentPaymentDto = this.studentPaymentService.getStudentPaymentById(studentPaymentId);
        return new ResponseEntity<>(studentPaymentDto, HttpStatus.OK);
    }

    @DeleteMapping("/{studentPaymentId}")
    public ApiResponse deteleStudentPayment(@PathVariable Long studentPaymentId){
        this.studentPaymentService.deleteStudentPayment(studentPaymentId);
        return new ApiResponse("Delete successful",true);
    }

    @PutMapping("/{studentPaymentId}")
    public ResponseEntity<StudentPaymentDto> updateStudentPayment(@RequestBody StudentPaymentDto studentPaymentDto, @PathVariable Long studentPaymentId){
        StudentPaymentDto updatedStudentPayment = this.studentPaymentService.updateStudentPayment(studentPaymentDto, studentPaymentId);
        return new ResponseEntity<>(updatedStudentPayment, HttpStatus.OK);
    }

    @GetMapping("/totalPayment")
    public Integer getTotalPayment(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "studentId", required = false) Integer studentId){
        Integer totalPayment = this.studentPaymentService.getMonthlyPayment(startDate, endDate, studentId);
        return totalPayment;
    }
}
