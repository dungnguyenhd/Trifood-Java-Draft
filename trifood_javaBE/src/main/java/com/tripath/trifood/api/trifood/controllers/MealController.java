package com.tripath.trifood.api.trifood.controllers;

import com.tripath.trifood.api.trifood.dto.FoodAmountDto;
import com.tripath.trifood.api.trifood.dto.MealDto;
import com.tripath.trifood.api.trifood.response.ApiResponse;
import com.tripath.trifood.api.trifood.services.service.customReturn.FoodAmountReturnService;
import com.tripath.trifood.api.trifood.services.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal")
public class MealController {

    @Autowired
    private MealService mealService;

    @PostMapping("/createEWeeklySchedule")
    public Long createEWeeklySchedule(){
        Long id = this.mealService.createEWeeklySchedule();
        return id;
    }

    @PostMapping("/{eWeeklyScheduleId}/{eDay}")
    public ResponseEntity<MealDto> createMeal(
            @RequestBody MealDto mealDto,
            @PathVariable Long eWeeklyScheduleId,
            @PathVariable Long eDay)
    {
        MealDto createMeal = this.mealService.createMeal(mealDto, eWeeklyScheduleId, eDay);
        return new ResponseEntity<>(createMeal, HttpStatus.CREATED);
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
        updatedMeal.setFood(mealDto.getFood());
        updatedMeal.setMealName(mealDto.getMealName());
        updatedMeal.setEDailySchedule(mealDto.getEDailySchedule());
        return new ResponseEntity<>(updatedMeal, HttpStatus.OK);
    }

    @GetMapping("/getTotalFoodAmount")
    public ResponseEntity<List<FoodAmountReturnService>> getTotalFoodAmount(
            @RequestParam(value = "weekNumber", required = false) Integer weekNumber,
            @RequestParam(value = "weekYear", required = false) Integer weekYear,
            @RequestParam(value = "eGroupId", required = false) Long eGroupId
    ){
        List<FoodAmountReturnService> totalFoodAmount = this.mealService.countTotalFoodAmount(weekNumber, weekYear, eGroupId);
        return new ResponseEntity<>(totalFoodAmount, HttpStatus.OK);
    }

    @GetMapping("/getTotalFoodDelete")
    public ResponseEntity<List<FoodAmountDto>> getTotalFoodDelete(
            @RequestParam(value = "weekNumber", required = false) Integer weekNumber,
            @RequestParam(value = "weekYear", required = false) Integer weekYear,
            @RequestParam(value = "eGroupId", required = false) Long eGroupId
    ){
        List<FoodAmountDto> totalFoodDelete = this.mealService.countTotalFoodDelete(weekNumber, weekYear, eGroupId);
        return new ResponseEntity<>(totalFoodDelete, HttpStatus.OK);
    }
}
