package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.entities.AssignSchedule;
import org.springframework.stereotype.Service;

@Service
public interface AssignScheduleService {
    void createAssignScheduleForAllWeek(Long eGroupId, Long eWeeklyScheduleId);

    void createAssignScheduleForSingleWeek(Long eGroupId, Integer weekNumber, Integer weekYear, Long eWeeklyScheduleId);

    AssignSchedule updateAssignScheduleForSingleWeek(AssignSchedule assignSchedule ,Long eGroupId, Integer weekNumber, Integer weekYear, Long eWeeklyScheduleId);
}
