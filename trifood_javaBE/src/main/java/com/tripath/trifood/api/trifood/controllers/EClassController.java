package com.tripath.trifood.api.trifood.controllers;

import com.tripath.trifood.api.student.dto.StudentDto;
import com.tripath.trifood.api.trifood.dto.EClassDto;
import com.tripath.trifood.api.trifood.response.EClassResponse;
import com.tripath.trifood.api.trifood.response.ApiResponse;
import com.tripath.trifood.api.trifood.services.service.EClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eClass")
public class EClassController {

    @Autowired
    private EClassService eClassService;

    @PostMapping("")
    public ResponseEntity<EClassDto> createEClass(@RequestBody EClassDto eClassDto){
        EClassDto createEClass = this.eClassService.createEClass(eClassDto);
        return new ResponseEntity<>(createEClass, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<EClassResponse> getAllEClasses(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "eClassId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        EClassResponse eClassResponse = this.eClassService.getAllEClass(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(eClassResponse, HttpStatus.OK) ;
    }

    @GetMapping("/{eClassId}")
    public ResponseEntity<EClassDto> getEClassById(@PathVariable Long eClassId){
        EClassDto eClassDto = this.eClassService.getEClassById(eClassId);
        return new ResponseEntity<>(eClassDto, HttpStatus.OK);
    }

    @DeleteMapping("/{eClassId}")
    public ApiResponse deleteEClass(@PathVariable Long eClassId){
        this.eClassService.deleteEClass(eClassId);
        return new ApiResponse("Delete successful",true);
    }

    @PutMapping("/{eClassId}")
    public ResponseEntity<EClassDto> updateEClass(@RequestBody EClassDto eClassDto, @PathVariable Long eClassId){
        EClassDto updatedEClass = this.eClassService.updateEClass(eClassDto, eClassId);
        return new ResponseEntity<>(updatedEClass, HttpStatus.OK);
    }

    @GetMapping("/seach/{keyword}")
    public ResponseEntity<EClassResponse> searchEClassByName(
            @PathVariable("keyword") String keyword,
                @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                @RequestParam(value = "sortBy",defaultValue = "e_class_name", required = false) String sortBy,
                @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir)
    {
        EClassResponse result = this.eClassService.searchEClass(keyword, pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/findClassesOfGroup")
    public ResponseEntity<EClassResponse> findClassesOfGroup(
            @RequestParam(value = "groupId", defaultValue = "1", required = false) Long groupId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "e_class_id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        EClassResponse result = this.eClassService.findClassesOfGroup(groupId, pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/sortClass")
    public ResponseEntity<EClassResponse> sortClass(
            @RequestParam(value = "classLevel", required = false) String classLevel,
            @RequestParam(value = "classGrade", required = false) String classGrade,
            @RequestParam(value = "className", required = false) String className,
            @RequestParam(value = "startYear", required = false) Integer startYear,
            @RequestParam(value = "endYear", required = false) Integer endYear,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize
    ){
        EClassResponse result = this.eClassService.sortClass(classLevel, classGrade, className, startYear, endYear, pageNumber, pageSize);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @GetMapping("/findStudentsOfClass")
//    public ResponseEntity<List<StudentDto>> findStudentsOfClass(
//            @RequestParam(value = "classId", defaultValue = "1", required = false) Integer classId
//    ){
//        List<StudentDto> result = this.eClassService.findStudentsOfClass(classId);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

}
