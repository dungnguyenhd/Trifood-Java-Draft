package com.tripath.trifood.api.trifood.controllers;

import com.tripath.trifood.api.trifood.dto.StudentPaymentDto;
import com.tripath.trifood.api.trifood.response.ApiResponse;
import com.tripath.trifood.api.trifood.response.StudentPaymentResponse;
import com.tripath.trifood.api.trifood.services.service.StudentPaymentService;
import com.tripath.trifood.entities.StudentPayment;
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
    public ResponseEntity<StudentPayment> createStudentPayment(
            @RequestParam(value = "studentId", required = true) Long studentId,
            @RequestParam(value = "weekMonth", required = true) Integer weekMonth,
            @RequestParam(value = "weekYear", required = true) Integer weekYear)
    {
        StudentPayment createStudentPayment = this.studentPaymentService.createStudentPayment(studentId, weekMonth, weekYear);
        return new ResponseEntity<>(createStudentPayment, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<StudentPaymentResponse> getAllStudentPayments(
            @RequestParam(value = "payMonth", required = true) Integer payMonth,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "student_fullname", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        StudentPaymentResponse studentPaymentResponse = this.studentPaymentService.getAllStudentPayment(payMonth , pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(studentPaymentResponse, HttpStatus.OK) ;
    }

    @GetMapping("/sortPayment")
    public ResponseEntity<StudentPaymentResponse> sortPayment(
            @RequestParam(value = "payMonth", defaultValue = "0", required = true) Integer payMonth,
            @RequestParam(value = "studentName", required = false) String studentName,
            @RequestParam(value = "classLevel", required = false) String classLevel,
            @RequestParam(value = "classGrade", required = false) String classGrade,
            @RequestParam(value = "className", required = false) String className,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "student_fullname", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        StudentPaymentResponse studentPaymentResponse = this.studentPaymentService.sortPayment(payMonth, classLevel, classGrade, className, studentName, pageNumber, pageSize, sortBy, sortDir);
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

    @PatchMapping("/{studentPaymentId}")
    public ResponseEntity<StudentPaymentDto> updateStudentPayment(@PathVariable Long studentPaymentId){
        StudentPaymentDto updatedStudentPayment = this.studentPaymentService.updateStudentPayment(studentPaymentId);
        return new ResponseEntity<>(updatedStudentPayment, HttpStatus.OK);
    }
}
