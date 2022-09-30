package com.tripath.trifood.api.student.service;

import com.tripath.trifood.api.student.dto.StudentDto;
import com.tripath.trifood.api.student.repository.RoleRepository;
import com.tripath.trifood.api.student.repository.StudentRepository;
import com.tripath.trifood.common.config.AppConstants;
import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.entities.Role;
import com.tripath.trifood.entities.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = this.dtoToStudent(studentDto);
        Student savedStudent = this.studentRepository.save(student);
        this.studentRepository.save(savedStudent);
        return null;
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto, Integer studentId) {
        Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student", " Id", studentId));
        student.setName(studentDto.getName());
        student.setPassword(studentDto.getPassword());
        student.setFullname(studentDto.getFullname());
        student.setStclass(studentDto.getStclass());
        student.setEmail(studentDto.getEmail());
        student.setPhone(studentDto.getPhone());
        Student updatedStudent = this.studentRepository.save(student);
        StudentDto studentDto1 = this.studentToDto(updatedStudent);

        return studentDto1;
    }

    @Override
    public StudentDto getStudentById(Integer studentId) {
        Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student", " Id", 0));
        return this.studentToDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents(){
        List<Student> students = this.studentRepository.findAll();
        List<StudentDto> studentDtos = students.stream().map(student-> this.studentToDto(student)).collect(Collectors.toList());
        return studentDtos;
    }

    @Override
    public void deleteStudent(Integer studentId) {
        Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student", " Id", studentId));
        this.studentRepository.delete(student);
    }

    public Student dtoToStudent(StudentDto studentDto){
        Student student = this.modelMapper.map(studentDto, Student.class);
        return student;
    }

    public StudentDto studentToDto(Student student){
        StudentDto studentDto = this.modelMapper.map(student, StudentDto.class);
        return studentDto;
    }

    @Override
    public StudentDto registerNewStudent(StudentDto studentDto){
        Student student = this.modelMapper.map(studentDto, Student.class);
        student.setPassword(this.passwordEncoder.encode(student.getPassword()));
        Role role = this.roleRepository.findById(AppConstants.NORMAL_USER).get();
        student.getRoles().add(role);
        Student newStudent = this.studentRepository.save(student);

        return this.modelMapper.map(newStudent, StudentDto.class);
    }
}
