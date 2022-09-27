package com.tripath.trifood.services.impl;

import com.tripath.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.models.GroupSchedule;
import com.tripath.trifood.payloads.Dto.GroupScheduleDto;
import com.tripath.trifood.payloads.response.GroupScheduleResponse;
import com.tripath.trifood.repositories.GroupScheduleRespository;
import com.tripath.trifood.services.service.GroupScheduleService;
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
public class GroupScheduleServiceImpl implements GroupScheduleService {

    @Autowired
    private GroupScheduleRespository groupScheduleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public GroupScheduleDto createGroupSchedule(GroupScheduleDto groupScheduleDto) {
        GroupSchedule groupSchedule = this.modelMapper.map(groupScheduleDto, GroupSchedule.class);
        GroupSchedule newGroupSchedule = this.groupScheduleRepo.save(groupSchedule);
        return this.modelMapper.map(newGroupSchedule, GroupScheduleDto.class);
    }

    @Override
    public GroupScheduleDto updateGroupSchedule(GroupScheduleDto groupScheduleDto, Integer groupScheduleId) {
        GroupSchedule groupSchedule = this.groupScheduleRepo.findById(groupScheduleId).orElseThrow(()-> new ResourceNotFoundException("GroupSchedule", "groupScheduleId", groupScheduleId));
        groupSchedule.setEGroupScheduleEndYear(groupScheduleDto.getEGroupScheduleEndYear());
        groupSchedule.setEGroupScheduleStartYear(groupScheduleDto.getEGroupScheduleStartYear());
        groupSchedule.setEGroupScheduleDay(groupScheduleDto.getEGroupScheduleDay());
        groupSchedule.setEGroup(groupScheduleDto.getEGroup());

        GroupSchedule updatedGroupSchedule = this.groupScheduleRepo.save(groupSchedule);
        return this.modelMapper.map(updatedGroupSchedule, GroupScheduleDto.class);
    }

    @Override
    public void deleteGroupSchedule(Integer groupScheduleId) {
        GroupSchedule groupSchedule = this.groupScheduleRepo.findById(groupScheduleId).orElseThrow(()-> new ResourceNotFoundException("GroupSchedule", "groupScheduleId", groupScheduleId));
        this.groupScheduleRepo.delete(groupSchedule);
    }

    @Override
    public GroupScheduleResponse getAllGroupSchedule(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<GroupSchedule> pageGroupSchedule = this.groupScheduleRepo.findAll(p);
        List<GroupSchedule> allGroupSchedule = pageGroupSchedule.getContent();
        List<GroupScheduleDto> groupScheduleDtos = allGroupSchedule.stream().map((groupSchedule) -> this.modelMapper.map(groupSchedule, GroupScheduleDto.class)).collect(Collectors.toList());

        GroupScheduleResponse groupScheduleResponse = new GroupScheduleResponse();
        groupScheduleResponse.setContent(groupScheduleDtos);
        groupScheduleResponse.setPageNumber(pageGroupSchedule.getNumber());
        groupScheduleResponse.setTotalElements(pageGroupSchedule.getTotalElements());
        groupScheduleResponse.setTotalPages(pageGroupSchedule.getTotalPages());
        groupScheduleResponse.setLastPage(pageGroupSchedule.isLast());

        return groupScheduleResponse;
    }

    @Override
    public GroupScheduleDto getGroupScheduleById(Integer groupScheduleId) {
        GroupSchedule groupSchedule = this.groupScheduleRepo.findById(groupScheduleId).orElseThrow(()-> new ResourceNotFoundException("GroupSchedule", "groupScheduleId", groupScheduleId));
        return this.modelMapper.map(groupSchedule, GroupScheduleDto.class);
    }

}
