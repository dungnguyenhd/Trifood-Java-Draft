package com.tripath.trifood.api.trifood.controllers;

import com.tripath.trifood.api.trifood.dto.GroupScheduleDto;
import com.tripath.trifood.api.trifood.response.ApiResponse;
import com.tripath.trifood.api.trifood.response.GroupScheduleResponse;
import com.tripath.trifood.api.trifood.services.service.GroupScheduleService;
import com.tripath.trifood.entities.GroupSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/groupSchedule")
public class GroupScheduleController {
    @Autowired
    private GroupScheduleService groupScheduleService;

    @PostMapping("")
    public ResponseEntity<GroupScheduleDto> createGroupSchedule(@RequestBody GroupScheduleDto groupScheduleDto)
    {
        GroupScheduleDto createGroupSchedule = this.groupScheduleService.createGroupSchedule(groupScheduleDto);
        return new ResponseEntity<>(createGroupSchedule, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<GroupScheduleResponse> getAllGroupSchedules(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "eGroupScheduleId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir)
    {
        GroupScheduleResponse groupScheduleResponse = this.groupScheduleService.getAllGroupSchedule(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(groupScheduleResponse, HttpStatus.OK) ;
    }

    @GetMapping("/{groupScheduleId}")
    public ResponseEntity<GroupScheduleDto> getGroupScheduleById(@PathVariable Integer groupScheduleId)
    {
        GroupScheduleDto groupScheduleDto = this.groupScheduleService.getGroupScheduleById(groupScheduleId);
        return new ResponseEntity<>(groupScheduleDto, HttpStatus.OK);
    }

    @DeleteMapping("/{groupScheduleId}")
    public ApiResponse deleteGroupSchedule(@PathVariable Integer groupScheduleId)
    {
        this.groupScheduleService.deleteGroupSchedule(groupScheduleId);
        return new ApiResponse("Delete successful",true);
    }

    @PutMapping("/{groupScheduleId}")
    public ResponseEntity<GroupScheduleDto> updateGroupSchedule(@RequestBody GroupScheduleDto groupScheduleDto, @PathVariable Integer groupScheduleId)
    {
        GroupScheduleDto updatedGroupSchedule = this.groupScheduleService.updateGroupSchedule(groupScheduleDto, groupScheduleId);
        return new ResponseEntity<>(updatedGroupSchedule, HttpStatus.OK);
    }

    @GetMapping("/getPayment")
    public Integer getTotalPayment(
            @RequestParam(value = "startDate",required = false) String startDate,
            @RequestParam(value = "endDate",required = false) String endDate,
            @RequestParam(value = "groupId",required = false) Integer groupId)
    {
        Integer totalPayment = this.groupScheduleService.getTotalPayment(startDate, endDate, groupId);
        return totalPayment;
    }

    @GetMapping("/findGroupScheduleByGroupName")
    public ResponseEntity<GroupSchedule> findGroupSchedule(@RequestParam(value = "groupName", required = false) String groupName)
    {
        GroupSchedule groupSchedules = this.groupScheduleService.findGroupScheduleByGroupName(groupName);
        return new ResponseEntity<>(groupSchedules, HttpStatus.OK) ;
    }

    @GetMapping("/findStudentScheduleByStudentId")
    public ResponseEntity<GroupSchedule> findStudentSchedule(@RequestParam(value = "studentId", required = false) Integer studentId)
    {
        GroupSchedule groupSchedules = this.groupScheduleService.findStudentScheduleByStudentId(studentId);
        return new ResponseEntity<>(groupSchedules, HttpStatus.OK) ;
    }

}
