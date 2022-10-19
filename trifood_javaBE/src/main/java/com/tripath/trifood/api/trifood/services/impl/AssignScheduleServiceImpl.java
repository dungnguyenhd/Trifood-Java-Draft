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
    WeekScheduleRepo weekScheduleRepo;

    @Autowired
    AssignScheduleRepo assignScheduleRepo;

    @Autowired
    EWeeklyScheduleRepo eWeeklyScheduleRepo;

    @Override
    public void createAssignScheduleForAllWeek(Long eGroupId, Long eWeeklyScheduleId){
        List<Long> listWeekId = weekScheduleRepo.getWeekIdByEGroupId(eGroupId);
        listWeekId.forEach((id) -> {
                AssignSchedule assignSchedule = new AssignSchedule();
                assignSchedule.setWeekSchedule(this.weekScheduleRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("weekSchedule", "eGroupId", id)));
                assignSchedule.setEWeeklySchedule(this.eWeeklyScheduleRepo.findById(eWeeklyScheduleId).orElseThrow(()-> new ResourceNotFoundException("eWeekSchedule", "eWeekScheduleId", eWeeklyScheduleId)));
                this.assignScheduleRepo.save(assignSchedule);
        });
    }

    @Override
    public void createAssignScheduleForSingleWeek(Long eGroupId,Integer weekNumber, Integer weekYear, Long eWeeklyScheduleId) {
        Long weekId = weekScheduleRepo.getSingleWeekIdByEGroupId(eGroupId, weekNumber, weekYear);
        AssignSchedule assignSchedule = new AssignSchedule();
        assignSchedule.setWeekSchedule(this.weekScheduleRepo.findById(weekId).orElseThrow(()-> new ResourceNotFoundException("eWeekSchedule", "eWeekScheduleId", weekId)));
        assignSchedule.setEWeeklySchedule(this.eWeeklyScheduleRepo.findById(eWeeklyScheduleId).orElseThrow(()-> new ResourceNotFoundException("eWeekSchedule", "eWeekScheduleId", eWeeklyScheduleId)));
    }

    @Override
    public AssignSchedule updateAssignScheduleForSingleWeek(AssignSchedule assignSchedule,Long eGroupId, Integer weekNumber, Integer weekYear, Long eWeeklyScheduleId) {
        Long weekId = weekScheduleRepo.getSingleWeekIdByEGroupId(eGroupId, weekNumber, weekYear);
        AssignSchedule newassignSchedule = this.assignScheduleRepo.findByWeekIdAndEWeeklyId(weekId, eWeeklyScheduleId);
        newassignSchedule.setEWeeklySchedule(assignSchedule.getEWeeklySchedule());
        newassignSchedule.setWeekSchedule(assignSchedule.getWeekSchedule());

        AssignSchedule updatedAssignSchedule = this.assignScheduleRepo.save(newassignSchedule);
        return updatedAssignSchedule;
    }
}
