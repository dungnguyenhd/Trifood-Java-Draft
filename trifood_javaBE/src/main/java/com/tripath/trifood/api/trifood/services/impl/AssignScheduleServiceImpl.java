package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.trifood.services.service.AssignScheduleService;
import com.tripath.trifood.api.trifood.services.service.customReturn.ScheduleReturnService;
import com.tripath.trifood.repositories.trifood.AssignScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignScheduleServiceImpl implements AssignScheduleService {
    @Autowired
    AssignScheduleRepo assignRepo;

    public List<ScheduleReturnService> findScheduleOfGroup(Long eGroupId, Integer weekNumer, Integer weekYear){
         List<ScheduleReturnService> result = assignRepo.findScheduleOfGroup(eGroupId, weekNumer, weekYear);
         return result;
    }

    public List<ScheduleReturnService> findScheduleOfStudent(Long studentId, Integer weekNumer, Integer weekYear){
        List<ScheduleReturnService> result = assignRepo.findScheduleOfGroup(studentId, weekNumer, weekYear);
        return result;
    }
}
