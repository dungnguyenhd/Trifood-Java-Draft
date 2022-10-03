package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.dto.GroupScheduleDto;
import com.tripath.trifood.api.trifood.response.GroupScheduleResponse;
import com.tripath.trifood.entities.GroupSchedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupScheduleService {

    GroupScheduleDto createGroupSchedule(GroupScheduleDto groupScheduleDto);

    GroupScheduleDto updateGroupSchedule(GroupScheduleDto groupScheduleDto, Integer groupScheduleId);

    void deleteGroupSchedule(Integer groupScheduleId);
    GroupScheduleResponse getAllGroupSchedule(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    GroupScheduleDto getGroupScheduleById(Integer groupScheduleId);

    Integer getDailyPayment(String mealDate, Integer groupId);

    List<GroupScheduleDto> findGroupSchedule(String startDate, String endDate, Integer groupId);
}
