package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.api.trifood.services.service.AssignScheduleService;
import com.tripath.trifood.entities.AssignSchedule;
import com.tripath.trifood.repositories.trifood.AssignScheduleRepo;
import com.tripath.trifood.repositories.trifood.EWeeklyScheduleRepo;
import com.tripath.trifood.repositories.trifood.WeekScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignScheduleServiceImpl implements AssignScheduleService {
    @Autowired
    WeekScheduleRepo weekRepo;
    @Autowired
    AssignScheduleRepo assignRepo;
    @Autowired
    EWeeklyScheduleRepo weeklyRepo;

    @Override
    public void createAssignScheduleForAllWeek(Long eGroupId, Long eWeeklyScheduleId){
        List<Long> listWeekId = weekRepo.findAllWeekIdByEGroupId(eGroupId);
        listWeekId.forEach((id) -> {
                AssignSchedule assign = new AssignSchedule();
                assign.setWeekSchedule(this.weekRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("weekSchedule", "eGroupId", id)));
                assign.setEWeeklySchedule(this.weeklyRepo.findById(eWeeklyScheduleId).orElseThrow(()-> new ResourceNotFoundException("eWeekSchedule", "eWeekScheduleId", eWeeklyScheduleId)));
                this.assignRepo.save(assign);
        });
    }

    @Override
    public void createAssignScheduleForSingleWeek(Long eGroupId,Integer weekNumber, Integer weekYear, Long eWeeklyScheduleId) {
        Long weekId = weekRepo.findSingleWeekIdByEGroupId(eGroupId, weekNumber, weekYear);
        AssignSchedule assign = new AssignSchedule();
        assign.setWeekSchedule(this.weekRepo.findById(weekId).orElseThrow(()-> new ResourceNotFoundException("eWeekSchedule", "eWeekScheduleId", weekId)));
        assign.setEWeeklySchedule(this.weeklyRepo.findById(eWeeklyScheduleId).orElseThrow(()-> new ResourceNotFoundException("eWeekSchedule", "eWeekScheduleId", eWeeklyScheduleId)));
    }

    @Override
    public AssignSchedule updateAssignScheduleForSingleWeek(AssignSchedule assignSchedule,Long eGroupId, Integer weekNumber, Integer weekYear, Long eWeeklyScheduleId) {
        Long weekId = weekRepo.findSingleWeekIdByEGroupId(eGroupId, weekNumber, weekYear);
        AssignSchedule newAssign = this.assignRepo.findByWeekIdAndEWeeklyId(weekId, eWeeklyScheduleId);
        newAssign.setEWeeklySchedule(assignSchedule.getEWeeklySchedule());
        newAssign.setWeekSchedule(assignSchedule.getWeekSchedule());

        AssignSchedule updatedAssignSchedule = this.assignRepo.save(newAssign);
        return updatedAssignSchedule;
    }
}
