package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.dto.EGroupDto;
import com.tripath.trifood.api.trifood.response.EGroupResponse;
import org.springframework.stereotype.Service;

@Service
public interface EGroupService {
    EGroupDto createEGroup(EGroupDto eGroupDto);

    EGroupDto updateEGroup(EGroupDto EGroupDto, Long eGroupId);

    void deleteEGroup(Long eGroupId);

    EGroupResponse getAllEGroup(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    EGroupDto getEGroupById(Long eGroupId);

    EGroupResponse searchEGroup(String keyword, Integer pageNumber, Integer pageSize);
}
