package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.entities.EClass;
import com.tripath.trifood.api.trifood.dto.EClassDto;
import com.tripath.trifood.api.trifood.response.EClassResponse;
import com.tripath.trifood.repositories.trifood.EClassRepository;
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
    private EClassRepository eClassRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EClassDto createEClass(EClassDto eClassDto) {
        EClass eClass = this.modelMapper.map(eClassDto, EClass.class);
        EClass newEclass = this.eClassRepo.save(eClass);
        return this.modelMapper.map(newEclass, EClassDto.class);
    }

    @Override
    public EClassDto updateEClass(EClassDto eClassDto, Integer eClassId) {
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
    public void deleteEClass(Integer eClassId) {
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
    public EClassDto getEClassById(Integer eClassId) {
        EClass eClass = this.eClassRepo.findById(eClassId).orElseThrow(()-> new ResourceNotFoundException("EatingClass", "eClassId", eClassId));
        return this.modelMapper.map(eClass, EClassDto.class);
    }

    @Override
    public List<EClassDto> searchEClass(String keyword) {
        List<EClass> eClasses = this.eClassRepo.searchByName("%"+keyword+"%");
        List<EClassDto> eClassDtos = eClasses.stream().map((eClass)->this.modelMapper.map(eClass, EClassDto.class)).collect(Collectors.toList());
        return eClassDtos;
    }
}
