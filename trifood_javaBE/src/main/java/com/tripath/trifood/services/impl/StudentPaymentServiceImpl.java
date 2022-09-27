package com.tripath.trifood.services.impl;

import com.tripath.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.models.StudentPayment;
import com.tripath.trifood.payloads.Dto.StudentPaymentDto;
import com.tripath.trifood.payloads.response.StudentPaymentResponse;
import com.tripath.trifood.repositories.StudentPaymentRepository;
import com.tripath.trifood.services.service.StudentPaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentPaymentServiceImpl implements StudentPaymentService {

    @Autowired
    private StudentPaymentRepository studentPaymentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentPaymentDto createStudentPayment(StudentPaymentDto studentPaymentDto) {
        StudentPayment studentPayment = this.modelMapper.map(studentPaymentDto, StudentPayment.class);
        StudentPayment newEgroup = this.studentPaymentRepo.save(studentPayment);
        return this.modelMapper.map(newEgroup, StudentPaymentDto.class);
    }

    @Override
    public StudentPaymentDto updateStudentPayment(StudentPaymentDto studentPaymentDto, Integer studentPaymentId) {
        StudentPayment studentPayment = this.studentPaymentRepo.findById(studentPaymentId).orElseThrow(()-> new ResourceNotFoundException("StudentPayment", "studentPaymentId", studentPaymentId));
        studentPayment.setIsPaid(studentPaymentDto.getIsPaid());
        studentPayment.setPayDate(studentPaymentDto.getPayDate());
        studentPayment.setStudent(studentPaymentDto.getStudent());

        StudentPayment updatedStudentPayment = this.studentPaymentRepo.save(studentPayment);
        return this.modelMapper.map(updatedStudentPayment, StudentPaymentDto.class);
    }

    @Override
    public void deleteStudentPayment(Integer studentPaymentId) {
        StudentPayment studentPayment = this.studentPaymentRepo.findById(studentPaymentId).orElseThrow(()-> new ResourceNotFoundException("StudentPayment", "studentPaymentId", studentPaymentId));
        this.studentPaymentRepo.delete(studentPayment);
    }

    @Override
    public StudentPaymentResponse getAllStudentPayment(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<StudentPayment> pageStudentPayment = this.studentPaymentRepo.findAll(p);
        List<StudentPayment> allStudentPayment = pageStudentPayment.getContent();
        List<StudentPaymentDto> studentPaymentDtos = allStudentPayment.stream().map((studentPayment) -> this.modelMapper.map(studentPayment, StudentPaymentDto.class)).collect(Collectors.toList());

        StudentPaymentResponse studentPaymentResponse = new StudentPaymentResponse();
        studentPaymentResponse.setContent(studentPaymentDtos);
        studentPaymentResponse.setPageNumber(pageStudentPayment.getNumber());
        studentPaymentResponse.setTotalElements(pageStudentPayment.getTotalElements());
        studentPaymentResponse.setTotalPages(pageStudentPayment.getTotalPages());
        studentPaymentResponse.setLastPage(pageStudentPayment.isLast());

        return studentPaymentResponse;
    }

    @Override
    public StudentPaymentDto getStudentPaymentById(Integer studentPaymentId) {
        StudentPayment studentPayment = this.studentPaymentRepo.findById(studentPaymentId).orElseThrow(()-> new ResourceNotFoundException("StudentPayment", "studentPaymentId", studentPaymentId));
        return this.modelMapper.map(studentPayment, StudentPaymentDto.class);
    }
}
