package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.dto.EClassDto;
import com.tripath.trifood.api.trifood.response.EClassResponse;
import org.springframework.stereotype.Service;

@Service
public interface EClassService {
    EClassDto createEClass(EClassDto eClassDto);

    EClassDto updateEClass(EClassDto eClassDto, Long eClassId);

    void deleteEClass(Long eClassId);

    EClassResponse getAllEClass(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    EClassDto getEClassById(Long eClassId);

    EClassResponse searchEClass(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    EClassResponse findClassesOfGroup(Long groupId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    EClassResponse sortClass(String classLevel, String classGrade, String className, Integer startYear, Integer endYear, Integer pageNumber, Integer pageSize);

    //    List<StudentDto> findStudentsOfClass(Integer classId);
}
