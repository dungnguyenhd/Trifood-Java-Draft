package com.tripath.trifood.api.student.controller;

import java.util.List;

import com.tripath.trifood.api.student.dto.StudentDto;
import com.tripath.trifood.api.student.service.StudentService;
import com.tripath.trifood.api.trifood.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping("/")
	public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto){
		StudentDto createStudentDto = this.studentService.createStudent(studentDto);
		return new ResponseEntity<>(createStudentDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{studentId}")
	public ResponseEntity<StudentDto> updateStudent(@Valid @RequestBody StudentDto studentDto, @PathVariable("studentId") Integer uid){
		StudentDto updatedStudent = this.studentService.updateStudent(studentDto, uid);
		return ResponseEntity.ok(updatedStudent);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{studentId}")
	public ResponseEntity<ApiResponse> deleteStudent(@PathVariable("studentId") Integer uid){
		this.studentService.deleteStudent(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Student deleted Successfully", true), HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<StudentDto>> getAllStudents(){
		return ResponseEntity.ok(this.studentService.getAllStudents());
	}
	
	@GetMapping("/{studentId}")
	public ResponseEntity<StudentDto> getSingleStudent(@PathVariable Integer studentId){
		return ResponseEntity.ok(this.studentService.getStudentById(studentId));
	}
}
