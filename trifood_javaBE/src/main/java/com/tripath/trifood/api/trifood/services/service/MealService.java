package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.dto.FoodAmountDto;
import com.tripath.trifood.api.trifood.dto.MealDto;
import com.tripath.trifood.api.trifood.services.service.customReturn.FoodAmountReturnService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MealService {
    MealDto createMeal(MealDto mealDto, Integer mealWeek, Integer mealYear, Long eGroupId);

    MealDto updateMeal(MealDto mealDto, Long mealId);

    void deleteMeal(Long mealId);

    List<FoodAmountReturnService> countTotalFoodAmount(Integer weekNumber, Integer weekYear, Long eGroupId);

    List<FoodAmountReturnService> countTotalFoodDelete(Integer weekNumber, Integer weekYear, Long eGroupId);

    MealDto getMealById(Long mealId);

}
