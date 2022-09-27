package com.tripath.trifood.services.service;

import com.tripath.trifood.payloads.Dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    StudentDto registerNewStudent(StudentDto student);
    StudentDto createStudent(StudentDto student);
    StudentDto updateStudent(StudentDto student, Integer studentId);
    StudentDto getStudentById(Integer studentId);
    List<StudentDto> getAllStudents();
    void deleteStudent(Integer studentId);
}
