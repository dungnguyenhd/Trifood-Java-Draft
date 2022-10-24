package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.trifood.dto.FoodAmountDto;
import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.api.trifood.services.service.customReturn.FoodAmountReturnService;
import com.tripath.trifood.entities.AssignSchedule;
import com.tripath.trifood.entities.Meal;
import com.tripath.trifood.api.trifood.dto.MealDto;
import com.tripath.trifood.repositories.trifood.*;
import com.tripath.trifood.api.trifood.services.service.MealService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepo mealRepo;
    @Autowired
    AssignScheduleRepo assignRepo;
    @Autowired
    WeekScheduleRepo weekRepo;
    @Autowired
    StudentOrderRepo orderRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MealDto createMeal(MealDto mealDto, Integer mealWeek, Integer mealYear, Long eGroupId) {
        Meal meal = this.modelMapper.map(mealDto, Meal.class);
        List<Long> listWeekId = weekRepo.findAllWeekIdByEGroupId(eGroupId);
        Meal newMeal = this.mealRepo.save(meal);

        if(newMeal.getIsCircle()){
            listWeekId.forEach((id) -> {
                AssignSchedule assign = new AssignSchedule();
                assign.setMeal(newMeal);
                assign.setWeekSchedule(this.weekRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("weekSchedule", "eGroupId", id)));
                this.assignRepo.save(assign);
            });
        }else{
            AssignSchedule assign = new AssignSchedule();
            assign.setMeal(newMeal);
            assign.setWeekSchedule(this.weekRepo.findById(weekRepo.findWeekIdByWeekNumberOfGroup(mealWeek, mealYear, eGroupId)).orElseThrow(()-> new ResourceNotFoundException("weekSchedule", "eGroupId", mealWeek)));
        }

        return this.modelMapper.map(newMeal, MealDto.class);
    }

    @Override
    public MealDto updateMeal(MealDto mealDto, Long mealId) {
        Meal meal = this.mealRepo.findById(mealId).orElseThrow(()-> new ResourceNotFoundException("Meal", "mealId", mealId));
        Meal updatedMeal = this.mealRepo.save(meal);
        return this.modelMapper.map(updatedMeal, MealDto.class);
    }

    @Override
    public void deleteMeal(Long mealId) {
        Meal meal = this.mealRepo.findById(mealId).orElseThrow(()-> new ResourceNotFoundException("Meal", "mealId", mealId));
        this.mealRepo.delete(meal);
    }

    @Override
    public List<FoodAmountReturnService> countTotalFoodAmount(Integer weekNumber, Integer weekYear, Long eGroupId) {
        List<FoodAmountReturnService> totalFoodAmount = this.mealRepo.countTotalFoodAmount(eGroupId, weekNumber, weekYear);
        return totalFoodAmount;
    }

    @Override
    public List<FoodAmountReturnService> countTotalFoodDelete(Integer weekNumber, Integer weekYear, Long eGroupId) {
        List<FoodAmountReturnService> listDelete = this.mealRepo.countTotalFoodDelete(weekNumber, weekYear, eGroupId);
        return listDelete;
    }

    @Override
    public MealDto getMealById(Long mealId) {
        Meal meal = this.mealRepo.findById(mealId).orElseThrow(()-> new ResourceNotFoundException("Meal", "mealId", mealId));
        return this.modelMapper.map(meal, MealDto.class);
    }

}
