package com.tripath.trifood.services.service;

import com.tripath.trifood.payloads.Dto.GroupScheduleDto;
import com.tripath.trifood.payloads.response.GroupScheduleResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface GroupScheduleService {
    //create groupSchedule
    GroupScheduleDto createGroupSchedule(GroupScheduleDto groupScheduleDto);

    //update groupSchedule
    GroupScheduleDto updateGroupSchedule(GroupScheduleDto groupScheduleDto, Integer groupScheduleId);

    //delete groupSchedule
    void deleteGroupSchedule(Integer groupScheduleId);

    //get all groupSchedule
    GroupScheduleResponse getAllGroupSchedule(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get single  groupSchedule
    GroupScheduleDto getGroupScheduleById(Integer groupScheduleId);

    Integer getDailyPayment(String scheduleDate, Integer groupId);
}
