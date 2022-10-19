package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.entities.EDailySchedule;
import com.tripath.trifood.repositories.trifood.EDailyScheduleRepo;
import com.tripath.trifood.api.trifood.services.service.EDailyScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EDailyScheduleServiceImpl implements EDailyScheduleService {

    @Autowired
    private EDailyScheduleRepo groupScheduleRepo;

    @Override
    public void deleteEDailySchedule(Long eDailyScheduleId) {
        com.tripath.trifood.entities.EDailySchedule eDailySchedule = this.groupScheduleRepo.findById(eDailyScheduleId).orElseThrow(()-> new ResourceNotFoundException("GroupSchedule", "groupScheduleId", eDailyScheduleId));
        this.groupScheduleRepo.delete(eDailySchedule);
    }
}
