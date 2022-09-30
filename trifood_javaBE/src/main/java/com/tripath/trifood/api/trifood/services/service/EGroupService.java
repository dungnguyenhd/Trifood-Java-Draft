package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.dto.EGroupDto;
import com.tripath.trifood.api.trifood.response.EGroupResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EGroupService {
    EGroupDto createEGroup(EGroupDto eGroupDto);

    EGroupDto updateEGroup(EGroupDto EGroupDto, Integer eGroupId);

    void deleteEGroup(Integer eGroupId);

    EGroupResponse getAllEGroup(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    EGroupDto getEGroupById(Integer eGroupId);

    List<EGroupDto> searchEGroup(String keyword);
}
