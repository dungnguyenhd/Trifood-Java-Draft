package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.services.service.customReturn.EWeeklyScheduleReturnService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EWeeklyScheduleService {
    List<EWeeklyScheduleReturnService> findEWeeklyScheduleByGroup(Long eGroupId, Integer weekNumber);

    List<EWeeklyScheduleReturnService> findEWeeklyScheduleByStudent(Long studentId, Integer weekNumber);

    void deleteEWeeklySchedule(Long eWeeklyId);
}
