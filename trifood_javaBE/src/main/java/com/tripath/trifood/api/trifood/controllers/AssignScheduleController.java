package com.tripath.trifood.api.trifood.controllers;

import com.tripath.trifood.api.trifood.services.service.AssignScheduleService;
import com.tripath.trifood.entities.AssignSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/AssignSchedule")
public class AssignScheduleController {
    @Autowired
    AssignScheduleService assignScheduleService;

    @PostMapping("/createForAllWeek/{eGroupId}/{eWeeklyScheduleId}")
    public void createAssignScheduleForAllWeek(@PathVariable Long eGroupId, @PathVariable Long eWeeklyScheduleId){
        this.assignScheduleService.createAssignScheduleForAllWeek(eGroupId, eWeeklyScheduleId);
    }

    @PostMapping("/createForSingleWeek/{eGroupId}/{eWeeklyScheduleId}/{weekNumber}/{weekYear}")
    public void createAssignScheduleForAllWeek(
            @PathVariable Long eGroupId,
            @PathVariable Long eWeeklyScheduleId,
            @PathVariable Integer weekNumber,
            @PathVariable Integer weekYear){
        this.assignScheduleService.createAssignScheduleForSingleWeek(eGroupId, weekNumber, weekYear, eWeeklyScheduleId);
    }

    @PutMapping("/updateForSingleWeek/{eGroupId}/{eWeeklyScheduleId}/{weekNumber}/{weekYear}")
    public ResponseEntity<AssignSchedule> updateAssignScheduleForSingleWeek(
            @RequestBody AssignSchedule assignSchedule,
            @PathVariable Long eGroupId,
            @PathVariable Long eWeeklyScheduleId,
            @PathVariable Integer weekNumber,
            @PathVariable Integer weekYear
            )
    {
        AssignSchedule updatedAssignSchedule = this.assignScheduleService.updateAssignScheduleForSingleWeek(assignSchedule, eGroupId, weekNumber, weekYear, eWeeklyScheduleId);
        return new ResponseEntity<>(updatedAssignSchedule, HttpStatus.OK);
    }

}
