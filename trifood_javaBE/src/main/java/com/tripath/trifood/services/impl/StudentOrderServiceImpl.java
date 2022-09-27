package com.tripath.trifood.services.impl;

import com.tripath.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.models.StudentOrder;
import com.tripath.trifood.payloads.Dto.StudentOrderDto;
import com.tripath.trifood.payloads.response.StudentOrderResponse;
import com.tripath.trifood.repositories.StudentOrderRepository;
import com.tripath.trifood.services.service.StudentOrderService;
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
public class StudentOrderServiceImpl implements StudentOrderService {

    @Autowired
    private StudentOrderRepository studentOrderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentOrderDto createStudentOrder(StudentOrderDto studentOrderDto) {
        StudentOrder studentOrder = this.modelMapper.map(studentOrderDto, StudentOrder.class);
        StudentOrder newEgroup = this.studentOrderRepo.save(studentOrder);
        return this.modelMapper.map(newEgroup, StudentOrderDto.class);
    }

    @Override
    public StudentOrderDto updateStudentOrder(StudentOrderDto studentOrderDto, Long studentOrderId) {
        StudentOrder studentOrder = this.studentOrderRepo.findById(studentOrderId).orElseThrow(()-> new ResourceNotFoundException("StudentOrder", "studentOrderId", studentOrderId));
        studentOrder.setGroupSchedule(studentOrderDto.getGroupSchedule());
        studentOrder.setStudent(studentOrderDto.getStudent());
        studentOrder.setRegisterMeal(studentOrderDto.getRegisterMeal());
        studentOrder.setRegisterDate(studentOrderDto.getRegisterDate());

        StudentOrder updatedStudentOrder = this.studentOrderRepo.save(studentOrder);
        return this.modelMapper.map(updatedStudentOrder, StudentOrderDto.class);
    }

    @Override
    public void deleteStudentOrder(Long studentOrderId) {
        StudentOrder studentOrder = this.studentOrderRepo.findById(studentOrderId).orElseThrow(()-> new ResourceNotFoundException("StudentOrder", "studentOrderId", studentOrderId));
        this.studentOrderRepo.delete(studentOrder);
    }

    @Override
    public StudentOrderResponse getAllStudentOrder(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<StudentOrder> pageStudentOrder = this.studentOrderRepo.findAll(p);
        List<StudentOrder> allStudentOrder = pageStudentOrder.getContent();
        List<StudentOrderDto> studentOrderDtos = allStudentOrder.stream().map((studentOrder) -> this.modelMapper.map(studentOrder, StudentOrderDto.class)).collect(Collectors.toList());

        StudentOrderResponse studentOrderResponse = new StudentOrderResponse();
        studentOrderResponse.setContent(studentOrderDtos);
        studentOrderResponse.setPageNumber(pageStudentOrder.getNumber());
        studentOrderResponse.setTotalElements(pageStudentOrder.getTotalElements());
        studentOrderResponse.setTotalPages(pageStudentOrder.getTotalPages());
        studentOrderResponse.setLastPage(pageStudentOrder.isLast());

        return studentOrderResponse;
    }

    @Override
    public StudentOrderDto getStudentOrderById(Long studentOrderId) {
        StudentOrder studentOrder = this.studentOrderRepo.findById(studentOrderId).orElseThrow(()-> new ResourceNotFoundException("StudentOrder", "studentOrderId", studentOrderId));
        return this.modelMapper.map(studentOrder, StudentOrderDto.class);
    }
}
