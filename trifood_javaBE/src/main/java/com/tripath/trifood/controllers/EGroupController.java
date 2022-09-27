package com.tripath.trifood.controllers;

import com.tripath.trifood.payloads.Dto.EGroupDto;
import com.tripath.trifood.payloads.response.ApiResponse;
import com.tripath.trifood.payloads.response.EGroupResponse;
import com.tripath.trifood.services.service.EGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eGroup")
public class EGroupController {

    @Autowired
    private EGroupService eGroupService;

    @PostMapping("")
    public ResponseEntity<EGroupDto> createEGroup(@RequestBody EGroupDto eGroupDto){
        EGroupDto createEGroup = this.eGroupService.createEGroup(eGroupDto);
        return new ResponseEntity<>(createEGroup, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<EGroupResponse> getAllEGroups(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "eGroupId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        EGroupResponse eGroupResponse = this.eGroupService.getAllEGroup(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(eGroupResponse, HttpStatus.OK) ;
    }

    @GetMapping("/{eGroupId}")
    public ResponseEntity<EGroupDto> getEGroupById(@PathVariable Integer eGroupId){
        EGroupDto eGroupDto = this.eGroupService.getEGroupById(eGroupId);
        return new ResponseEntity<>(eGroupDto, HttpStatus.OK);
    }

    @DeleteMapping("/{eGroupId}")
    public ApiResponse deteleEGroup(@PathVariable Integer eGroupId){
        this.eGroupService.deleteEGroup(eGroupId);
        return new ApiResponse("Delete successful",true);
    }

    @PutMapping("/{eGroupId}")
    public ResponseEntity<EGroupDto> updateEGroup(@RequestBody EGroupDto eGroupDto, @PathVariable Integer eGroupId){
        EGroupDto updatedEGroup = this.eGroupService.updateEGroup(eGroupDto, eGroupId);
        return new ResponseEntity<>(updatedEGroup, HttpStatus.OK);
    }

    @GetMapping("/seach/{keyword}")
    public ResponseEntity<List<EGroupDto>> searchEGroupByName(@PathVariable("keyword") String keyword){
        List<EGroupDto> result = this.eGroupService.searchEGroup(keyword);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
