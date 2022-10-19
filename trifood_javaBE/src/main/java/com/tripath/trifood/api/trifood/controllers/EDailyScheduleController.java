package com.tripath.trifood.api.trifood.controllers;

import com.tripath.trifood.api.trifood.response.ApiResponse;
import com.tripath.trifood.api.trifood.services.service.EDailyScheduleService;
import com.tripath.trifood.entities.EDailySchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/groupSchedule")
public class EDailyScheduleController {
    @Autowired
    private EDailyScheduleService eDailyScheduleService;

    @DeleteMapping("/{groupScheduleId}")
    public ApiResponse deleteEDailySchedule(@PathVariable Long eDailyScheduleId)
    {
        this.eDailyScheduleService.deleteEDailySchedule(eDailyScheduleId);
        return new ApiResponse("Delete successful",true);
    }
}
