package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.services.service.customReturn.ScheduleReturnService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssignScheduleService {
    List<ScheduleReturnService> findScheduleOfGroup(Long eGroupId, Integer weekNumer, Integer weekYear);

    List<ScheduleReturnService> findScheduleOfStudent(Long studentId, Integer weekNumber, Integer weekYear);
}
