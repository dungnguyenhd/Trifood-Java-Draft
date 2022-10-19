package com.tripath.trifood.api.student.service;

import com.tripath.trifood.api.student.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    StudentDto registerNewStudent(StudentDto student);
    StudentDto createStudent(StudentDto student);
    StudentDto updateStudent(StudentDto student, Long studentId);
    StudentDto getStudentById(Long studentId);
    List<StudentDto> getAllStudents();
    void deleteStudent(Long studentId);
}
