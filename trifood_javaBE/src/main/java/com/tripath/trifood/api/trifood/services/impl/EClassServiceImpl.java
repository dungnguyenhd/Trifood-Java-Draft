package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.entities.EClass;
import com.tripath.trifood.api.trifood.dto.EClassDto;
import com.tripath.trifood.api.trifood.response.EClassResponse;
import com.tripath.trifood.repositories.trifood.EClassRepo;
import com.tripath.trifood.api.trifood.services.service.EClassService;
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
public class EClassServiceImpl implements EClassService {

    @Autowired
    private EClassRepo eClassRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EClassDto createEClass(EClassDto eClassDto) {
        EClass eClass = this.modelMapper.map(eClassDto, EClass.class);
        EClass newEclass = this.eClassRepo.save(eClass);
        return this.modelMapper.map(newEclass, EClassDto.class);
    }

    @Override
    public EClassDto updateEClass(EClassDto eClassDto, Long eClassId) {
        EClass eClass = this.eClassRepo.findById(eClassId).orElseThrow(()-> new ResourceNotFoundException("EatingClass", "eClassId", eClassId));
        eClass.setEClassName(eClassDto.getEClassName());
        eClass.setEClassGrade(eClassDto.getEClassGrade());
        eClass.setEClassLevel(eClassDto.getEClassLevel());
        eClass.setEClassStartYear(eClassDto.getEClassStartYear());
        eClass.setEClassEndYear(eClassDto.getEClassEndYear());
        eClass.setEGroup(eClassDto.getEGroup());

        EClass eClass1 = this.eClassRepo.save(eClass);
        return this.modelMapper.map(eClass1, EClassDto.class);
    }

    @Override
    public void deleteEClass(Long eClassId) {
        EClass eClass = this.eClassRepo.findById(eClassId).orElseThrow(()-> new ResourceNotFoundException("EatingClass", "eClassId", eClassId));
        this.eClassRepo.delete(eClass);
    }

    @Override
    public EClassResponse getAllEClass(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        // pagination
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<EClass> pageEClass = this.eClassRepo.findAll(p);
        List<EClass> allEClass = pageEClass.getContent();
        List<EClassDto> eClassDtos = allEClass.stream().map((eClass) -> this.modelMapper.map(eClass, EClassDto.class)).collect(Collectors.toList());
        EClassResponse eClassResponse = new EClassResponse();
        eClassResponse.setContent(eClassDtos);
        eClassResponse.setPageNumber(pageEClass.getNumber());
        eClassResponse.setTotalElements(pageEClass.getTotalElements());
        eClassResponse.setTotalPages(pageEClass.getTotalPages());
        eClassResponse.setLastPage(pageEClass.isLast());

        return eClassResponse;
    }

    @Override
    public EClassDto getEClassById(Long eClassId) {
        EClass eClass = this.eClassRepo.findById(eClassId).orElseThrow(()-> new ResourceNotFoundException("EatingClass", "eClassId", eClassId));
        return this.modelMapper.map(eClass, EClassDto.class);
    }

    @Override
    public EClassResponse searchEClass(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<EClass> eClasses = this.eClassRepo.searchByName("%"+keyword+"%", p);
        List<EClassDto> eClassDtos = eClasses.stream().map((eClass)->this.modelMapper.map(eClass, EClassDto.class)).collect(Collectors.toList());
        EClassResponse eClassResponse = new EClassResponse();
        eClassResponse.setContent(eClassDtos);
        eClassResponse.setPageNumber(eClasses.getNumber());
        eClassResponse.setTotalElements(eClasses.getTotalElements());
        eClassResponse.setTotalPages(eClasses.getTotalPages());
        eClassResponse.setLastPage(eClasses.isLast());

        return eClassResponse;
    }

    @Override
    public EClassResponse findClassesOfGroup(Long groupId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<EClass> listClasses = this.eClassRepo.findClassesOfGroup(groupId, p);
        List<EClass> allEClass = listClasses.getContent();
        List<EClassDto> eClassDtos = listClasses.stream().map((eClass)->this.modelMapper.map(eClass, EClassDto.class)).collect(Collectors.toList());
        EClassResponse eClassResponse = new EClassResponse();
        eClassResponse.setContent(eClassDtos);
        eClassResponse.setPageNumber(listClasses.getNumber());
        eClassResponse.setTotalElements(listClasses.getTotalElements());
        eClassResponse.setTotalPages(listClasses.getTotalPages());
        eClassResponse.setLastPage(listClasses.isLast());

        return eClassResponse;
    }

    @Override
    public EClassResponse sortClass(String classLevel, String classGrade, String className, Integer startYear, Integer endYear, Integer pageNumber, Integer pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<EClass> listClasses = this.eClassRepo.sortClass(classLevel, classGrade, className, startYear, endYear, p);
        List<EClassDto> eClassDtos = listClasses.stream().map((eClass)->this.modelMapper.map(eClass, EClassDto.class)).collect(Collectors.toList());
        EClassResponse eClassResponse = new EClassResponse();
        eClassResponse.setContent(eClassDtos);
        eClassResponse.setPageNumber(listClasses.getNumber());
        eClassResponse.setTotalElements(listClasses.getTotalElements());
        eClassResponse.setTotalPages(listClasses.getTotalPages());
        eClassResponse.setLastPage(listClasses.isLast());

        return eClassResponse;
    }

//    @Override
//    public List<StudentDto> findStudentsOfClass(Integer classId) {
//        List<Student> listStudents = this.eClassRepo.findStudentsOfClass(classId);
//        List<StudentDto> listDto = listStudents.stream().map((student)->this.modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
//        return listDto;
//    }
}
