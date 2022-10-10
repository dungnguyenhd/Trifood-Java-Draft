package com.tripath.trifood.api.trifood.controllers;

import com.tripath.trifood.api.trifood.dto.StudentPaymentDto;
import com.tripath.trifood.api.trifood.response.ApiResponse;
import com.tripath.trifood.api.trifood.response.StudentPaymentResponse;
import com.tripath.trifood.api.trifood.services.service.StudentPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestParam(value = "startDate", defaultValue = "2022-02-10", required = true) String startDate,
            @RequestParam(value = "endDate", defaultValue = "2022-02-18", required = true) String endDate,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "student_fullname", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        StudentPaymentResponse studentPaymentResponse = this.studentPaymentService.getAllStudentPayment(startDate, endDate, pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(studentPaymentResponse, HttpStatus.OK) ;
    }

    @GetMapping("/searchPayment")
    public ResponseEntity<StudentPaymentResponse> searchPayment(
            @RequestParam(value = "startDate", defaultValue = "2022-02-10", required = true) String startDate,
            @RequestParam(value = "endDate", defaultValue = "2022-02-18", required = true) String endDate,
            @RequestParam(value = "studentName", defaultValue = "THPT", required = false) String studentName,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "student_fullname", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        StudentPaymentResponse studentPaymentResponse = this.studentPaymentService.searchPaymentByStudentName(startDate, endDate, studentName, pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(studentPaymentResponse, HttpStatus.OK) ;
    }

    @GetMapping("/sortPayment")
    public ResponseEntity<StudentPaymentResponse> sortPayment(
            @RequestParam(value = "startDate", defaultValue = "2022-02-10", required = true) String startDate,
            @RequestParam(value = "endDate", defaultValue = "2022-02-18", required = true) String endDate,
            @RequestParam(value = "classLevel", defaultValue = "THPT", required = false) String classLevel,
            @RequestParam(value = "classGrade", defaultValue = "5", required = false) String classGrade,
            @RequestParam(value = "className", defaultValue = "5A", required = false) String className,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "student_fullname", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        StudentPaymentResponse studentPaymentResponse = this.studentPaymentService.sortPayment(startDate, endDate, classLevel, classGrade, className, pageNumber, pageSize, sortBy, sortDir);
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

    @GetMapping("/monthlyPayment")
    public Integer getMonthlyPayment(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "studentId", required = false) Integer studentId){
        Integer totalPayment = this.studentPaymentService.getMonthlyPayment(startDate, endDate, studentId);
        return totalPayment;
    }

    @GetMapping("/getStudentTotalMeal")
    public Integer getTotalMeal(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "studentId", required = false) Integer studentId
    ){
        Integer totalMeal = this.studentPaymentService.getStudentTotalMeal(startDate, endDate, studentId);
        return totalMeal;
    }
}
