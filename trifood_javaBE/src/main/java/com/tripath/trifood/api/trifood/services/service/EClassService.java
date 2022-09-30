package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.dto.EClassDto;
import com.tripath.trifood.api.trifood.response.EClassResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EClassService {
    EClassDto createEClass(EClassDto eClassDto);

    EClassDto updateEClass(EClassDto eClassDto, Integer eClassId);

    void deleteEClass(Integer eClassId);

    EClassResponse getAllEClass(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    EClassDto getEClassById(Integer eClassId);

    List<EClassDto> searchEClass(String keyword);
}