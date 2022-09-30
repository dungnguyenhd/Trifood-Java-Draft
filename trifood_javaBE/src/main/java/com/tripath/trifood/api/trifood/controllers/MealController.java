package com.tripath.trifood.api.trifood.controllers;

import com.tripath.trifood.api.trifood.dto.MealDto;
import com.tripath.trifood.api.trifood.response.ApiResponse;
import com.tripath.trifood.api.trifood.response.MealResponse;
import com.tripath.trifood.api.trifood.services.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meal")
public class MealController {

    @Autowired
    private MealService mealService;

    @PostMapping("")
    public ResponseEntity<MealDto> createMeal(@RequestBody MealDto mealDto){
        MealDto createMeal = this.mealService.createMeal(mealDto);
        return new ResponseEntity<>(createMeal, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<MealResponse> getAllMeals(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        MealResponse mealResponse = this.mealService.getAllMeal(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(mealResponse, HttpStatus.OK) ;
    }

    @GetMapping("/{mealId}")
    public ResponseEntity<MealDto> getMealById(@PathVariable Long mealId){
        MealDto mealDto = this.mealService.getMealById(mealId);
        return new ResponseEntity<>(mealDto, HttpStatus.OK);
    }

    @DeleteMapping("/{mealId}")
    public ApiResponse deteleMeal(@PathVariable Long mealId){
        this.mealService.deleteMeal(mealId);
        return new ApiResponse("Delete successful",true);
    }

    @PutMapping("/{mealId}")
    public ResponseEntity<MealDto> updateMeal(@RequestBody MealDto mealDto, @PathVariable Long mealId){
        MealDto updatedMeal = this.mealService.updateMeal(mealDto, mealId);
        return new ResponseEntity<>(updatedMeal, HttpStatus.OK);
    }
}
