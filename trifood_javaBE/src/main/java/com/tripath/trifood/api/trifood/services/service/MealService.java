package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.dto.MealDto;
import com.tripath.trifood.api.trifood.response.MealResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MealService {

    MealDto createMeal(MealDto mealDto);

    MealDto updateMeal(MealDto mealDto, Long mealId);

    void deleteMeal(Long mealId);

    MealResponse getAllMeal(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    List<ScheduleReturnService> getMealFood(String startDate, String endDate, Integer groupScheduleId);

    List<FoodAmountReturnService> countTotalFoodAmount(String startDate, String endDate);

    MealDto getMealById(Long mealId);

}
