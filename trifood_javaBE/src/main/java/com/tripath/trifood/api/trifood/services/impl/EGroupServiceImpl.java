package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.api.trifood.services.service.EGroupService;
import com.tripath.trifood.entities.EGroup;
import com.tripath.trifood.api.trifood.dto.EGroupDto;
import com.tripath.trifood.api.trifood.response.EGroupResponse;
import com.tripath.trifood.entities.WeekSchedule;
import com.tripath.trifood.repositories.trifood.EGroupRepo;
import com.tripath.trifood.repositories.trifood.WeekScheduleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class EGroupServiceImpl implements EGroupService {
    @Autowired
    private EGroupRepo eGroupRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WeekScheduleRepo weekScheduleRepo;

    @Override
    public EGroupDto createEGroup(EGroupDto eGroupDto) {
        EGroup eGroup = this.modelMapper.map(eGroupDto, EGroup.class);
        EGroup newEgroup = this.eGroupRepo.save(eGroup);
        Calendar calendar = Calendar.getInstance(Locale.CHINESE);

        while (calendar.get(Calendar.YEAR) == Calendar.getInstance(Locale.CHINESE).get(Calendar.YEAR)) {
                WeekSchedule weekSchedule = new WeekSchedule();
                calendar.add(Calendar.WEEK_OF_YEAR, 1);
                weekSchedule.setWeekNumber(calendar.get(Calendar.WEEK_OF_YEAR));
                weekSchedule.setWeekMonth(calendar.get(Calendar.MONTH));
                weekSchedule.setWeekYear(calendar.get(Calendar.YEAR));
                weekSchedule.setEGroup(newEgroup);
                this.weekScheduleRepo.save(weekSchedule);
        }
        return this.modelMapper.map(newEgroup, EGroupDto.class);
    }

    @Override
    public EGroupDto updateEGroup(EGroupDto eGroupDto, Long eGroupId) {
        EGroup eGroup = this.eGroupRepo.findById(eGroupId).orElseThrow(()-> new ResourceNotFoundException("EatingGroup", "eGroupId", eGroupId));
        eGroup.setEGroupName(eGroupDto.getEGroupName());
        eGroup.setEGroupStartYear(eGroupDto.getEGroupStartYear());
        eGroup.setEGroupEndYear(eGroupDto.getEGroupEndYear());
        eGroup.setEGroupKey(eGroup.getEGroupKey());

        EGroup updatedEGroup = this.eGroupRepo.save(eGroup);
        return this.modelMapper.map(updatedEGroup, EGroupDto.class);
    }

    @Override
    public void deleteEGroup(Long eGroupId) {
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
    public EGroupDto getEGroupById(Long eGroupId) {
        EGroup eGroup = this.eGroupRepo.findById(eGroupId).orElseThrow(()-> new ResourceNotFoundException("EatingGroup", "eGroupId", eGroupId));
        return this.modelMapper.map(eGroup, EGroupDto.class);
    }

    @Override
    public EGroupResponse searchEGroup(String keyword, Integer pageNumber, Integer pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<EGroup> eGroups = this.eGroupRepo.searchByName("%"+keyword+"%", p);
        List<EGroup> allEGroup = eGroups.getContent();
        List<EGroupDto> eGroupDtos = eGroups.stream().map((eGroup)->this.modelMapper.map(eGroup, EGroupDto.class)).collect(Collectors.toList());

        EGroupResponse eGroupResponse = new EGroupResponse();
        eGroupResponse.setContent(eGroupDtos);
        eGroupResponse.setPageNumber(eGroups.getNumber());
        eGroupResponse.setTotalElements(eGroups.getTotalElements());
        eGroupResponse.setTotalPages(eGroups.getTotalPages());
        eGroupResponse.setLastPage(eGroups.isLast());

        return eGroupResponse;
    }
}
