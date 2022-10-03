package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.trifood.dto.EClassDto;
import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.entities.EClass;
import com.tripath.trifood.entities.GroupSchedule;
import com.tripath.trifood.api.trifood.dto.GroupScheduleDto;
import com.tripath.trifood.api.trifood.response.GroupScheduleResponse;
import com.tripath.trifood.repositories.trifood.GroupScheduleRespository;
import com.tripath.trifood.api.trifood.services.service.GroupScheduleService;
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
        groupSchedule.setEGroupScheduleStartDate(groupScheduleDto.getEGroupScheduleStartDate());
        groupSchedule.setEGroupScheduleEndDate(groupScheduleDto.getEGroupScheduleEndDate());
        groupSchedule.setEGroup(groupScheduleDto.getEGroup());
        groupSchedule.setEGroupDailyPayment(groupScheduleDto.getEGroupDailyPayment());

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
        List<GroupScheduleDto> groupScheduleDtos = allGroupSchedule.stream().map((groupScheduleDto) -> this.modelMapper.map(groupScheduleDto, GroupScheduleDto.class)).collect(Collectors.toList());

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

    @Override
    public Integer getDailyPayment(String mealDate, Integer groupId){
        Integer dailyPayment = this.groupScheduleRepo.getTotalPayment(mealDate, groupId);
        return dailyPayment;
    }

    @Override
    public List<GroupScheduleDto> findGroupSchedule(String startDate, String endDate, Integer groupId) {
        List<GroupSchedule> groupSchedules = this.groupScheduleRepo.findGroupSchedule(startDate, endDate, groupId);
        List<GroupScheduleDto> groupScheduleDtos = groupSchedules.stream().map((eGroupSchedule)->this.modelMapper.map(eGroupSchedule, GroupScheduleDto.class)).collect(Collectors.toList());
        return groupScheduleDtos;
    }
}
