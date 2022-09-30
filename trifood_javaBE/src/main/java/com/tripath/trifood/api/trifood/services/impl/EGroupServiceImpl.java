package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.entities.EGroup;
import com.tripath.trifood.api.trifood.dto.EGroupDto;
import com.tripath.trifood.api.trifood.response.EGroupResponse;
import com.tripath.trifood.repositories.trifood.EGroupRepository;
import com.tripath.trifood.api.trifood.services.service.EGroupService;
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
public class EGroupServiceImpl implements EGroupService {

    @Autowired
    private EGroupRepository eGroupRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EGroupDto createEGroup(EGroupDto eGroupDto) {
        EGroup eGroup = this.modelMapper.map(eGroupDto, EGroup.class);
        EGroup newEgroup = this.eGroupRepo.save(eGroup);
        return this.modelMapper.map(newEgroup, EGroupDto.class);
    }

    @Override
    public EGroupDto updateEGroup(EGroupDto eGroupDto, Integer eGroupId) {
        EGroup eGroup = this.eGroupRepo.findById(eGroupId).orElseThrow(()-> new ResourceNotFoundException("EatingGroup", "eGroupId", eGroupId));
        eGroup.setEGroupName(eGroupDto.getEGroupName());
        eGroup.setEGroupStartYear(eGroupDto.getEGroupStartYear());
        eGroup.setEGroupEndYear(eGroupDto.getEGroupEndYear());

        EGroup updatedEGroup = this.eGroupRepo.save(eGroup);
        return this.modelMapper.map(updatedEGroup, EGroupDto.class);
    }

    @Override
    public void deleteEGroup(Integer eGroupId) {
        EGroup eGroup = this.eGroupRepo.findById(eGroupId).orElseThrow(()-> new ResourceNotFoundException("EatingGroup", "eGroupId", eGroupId));
        this.eGroupRepo.delete(eGroup);
    }

    @Override
    public EGroupResponse getAllEGroup(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<EGroup> pageEGroup = this.eGroupRepo.findAll(p);
        List<EGroup> allEGroup = pageEGroup.getContent();
        List<EGroupDto> eGroupDtos = allEGroup.stream().map((eGroup) -> this.modelMapper.map(eGroup, EGroupDto.class)).collect(Collectors.toList());

        EGroupResponse eGroupResponse = new EGroupResponse();
        eGroupResponse.setContent(eGroupDtos);
        eGroupResponse.setPageNumber(pageEGroup.getNumber());
        eGroupResponse.setTotalElements(pageEGroup.getTotalElements());
        eGroupResponse.setTotalPages(pageEGroup.getTotalPages());
        eGroupResponse.setLastPage(pageEGroup.isLast());

        return eGroupResponse;
    }

    @Override
    public EGroupDto getEGroupById(Integer eGroupId) {
        EGroup eGroup = this.eGroupRepo.findById(eGroupId).orElseThrow(()-> new ResourceNotFoundException("EatingGroup", "eGroupId", eGroupId));
        return this.modelMapper.map(eGroup, EGroupDto.class);
    }

    @Override
    public List<EGroupDto> searchEGroup(String keyword) {
        List<EGroup> eGroups = this.eGroupRepo.searchByName("%"+keyword+"%");
        List<EGroupDto> eGroupDtos = eGroups.stream().map((eGroup)->this.modelMapper.map(eGroup, EGroupDto.class)).collect(Collectors.toList());
        return eGroupDtos;
    }
}
