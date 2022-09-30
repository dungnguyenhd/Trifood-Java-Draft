package com.tripath.trifood.api.student.service;

import com.tripath.trifood.api.student.dto.StudentDto;
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
