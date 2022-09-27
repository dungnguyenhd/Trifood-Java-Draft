package com.tripath.trifood.controllers;

import com.tripath.trifood.payloads.Dto.GroupScheduleDto;
import com.tripath.trifood.payloads.response.ApiResponse;
import com.tripath.trifood.payloads.response.GroupScheduleResponse;
import com.tripath.trifood.services.service.GroupScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groupSchedule")
public class GroupScheduleController {

    @Autowired
    private GroupScheduleService groupScheduleService;

    @PostMapping("")
    public ResponseEntity<GroupScheduleDto> createGroupSchedule(@RequestBody GroupScheduleDto groupScheduleDto){
        GroupScheduleDto createGroupSchedule = this.groupScheduleService.createGroupSchedule(groupScheduleDto);
        return new ResponseEntity<>(createGroupSchedule, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<GroupScheduleResponse> getAllGroupSchedulees(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "eGroupScheduleId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        GroupScheduleResponse groupScheduleResponse = this.groupScheduleService.getAllGroupSchedule(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(groupScheduleResponse, HttpStatus.OK) ;
    }

    @GetMapping("/{groupScheduleId}")
    public ResponseEntity<GroupScheduleDto> getGroupScheduleById(@PathVariable Integer groupScheduleId){
        GroupScheduleDto groupScheduleDto = this.groupScheduleService.getGroupScheduleById(groupScheduleId);
        return new ResponseEntity<>(groupScheduleDto, HttpStatus.OK);
    }

    @DeleteMapping("/{groupScheduleId}")
    public ApiResponse deleteGroupSchedule(@PathVariable Integer groupScheduleId){
        this.groupScheduleService.deleteGroupSchedule(groupScheduleId);
        return new ApiResponse("Delete successful",true);
    }

    @PutMapping("/{groupScheduleId}")
    public ResponseEntity<GroupScheduleDto> updateGroupSchedule(@RequestBody GroupScheduleDto groupScheduleDto, @PathVariable Integer groupScheduleId){
        GroupScheduleDto updatedGroupSchedule = this.groupScheduleService.updateGroupSchedule(groupScheduleDto, groupScheduleId);
        return new ResponseEntity<>(updatedGroupSchedule, HttpStatus.OK);
    }
}
