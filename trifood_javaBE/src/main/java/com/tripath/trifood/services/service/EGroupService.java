package com.tripath.trifood.services.service;

import com.tripath.trifood.payloads.Dto.EGroupDto;
import com.tripath.trifood.payloads.response.EGroupResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EGroupService {
    EGroupDto createEGroup(EGroupDto eGroupDto);

    //update group
    EGroupDto updateEGroup(EGroupDto EGroupDto, Integer eGroupId);

    //delete group
    void deleteEGroup(Integer eGroupId);

    //get all eating groups
    EGroupResponse getAllEGroup(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get single eating group
    EGroupDto getEGroupById(Integer eGroupId);

    //search eating group
    List<EGroupDto> searchEGroup(String keyword);
}
