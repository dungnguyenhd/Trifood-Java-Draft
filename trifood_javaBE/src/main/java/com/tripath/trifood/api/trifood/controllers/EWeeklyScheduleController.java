package com.tripath.trifood.api.trifood.controllers;

import com.tripath.trifood.api.trifood.services.service.EWeeklyScheduleService;
import com.tripath.trifood.api.trifood.services.service.customReturn.EWeeklyScheduleReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/eWeeklySchedule")
public class EWeeklyScheduleController {
    @Autowired
    EWeeklyScheduleService eWeeklyService;

    @GetMapping("/findByGroup")
    public List<EWeeklyScheduleReturnService> findEWeeklyScheduleByGroup(
            @RequestParam(value = "groupId", required = true) Long groupId,
            @RequestParam(value = "weekNumber", required = true) Integer weekNumber
    ){
        List<EWeeklyScheduleReturnService> list = eWeeklyService.findEWeeklyScheduleByGroup(groupId, weekNumber);
        return list;
    }

    @GetMapping("/findByStudent")
    public List<EWeeklyScheduleReturnService> findEWeeklyScheduleByStudent(
            @RequestParam(value = "studentId", required = true) Long studentId,
            @RequestParam(value = "weekNumber", required = true) Integer weekNumber
    ){
        List<EWeeklyScheduleReturnService> list = eWeeklyService.findEWeeklyScheduleByStudent(studentId, weekNumber);
        return list;
    }

}
