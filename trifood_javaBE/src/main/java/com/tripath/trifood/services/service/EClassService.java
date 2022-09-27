package com.tripath.trifood.services.service;

import com.tripath.trifood.payloads.Dto.EClassDto;
import com.tripath.trifood.payloads.response.EClassResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EClassService {
    //create eating class
    EClassDto createEClass(EClassDto eClassDto);

    //update eating class
    EClassDto updateEClass(EClassDto eClassDto, Integer eClassId);

    //delete eating class
    void deleteEClass(Integer eClassId);

    //get all eating classes
    EClassResponse getAllEClass(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get single eating class
    EClassDto getEClassById(Integer eClassId);

    //search eating class
    List<EClassDto> searchEClass(String keyword);
}
