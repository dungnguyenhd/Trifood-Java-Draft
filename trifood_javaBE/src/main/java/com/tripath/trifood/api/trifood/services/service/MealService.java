package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.dto.FoodAmountDto;
import com.tripath.trifood.api.trifood.dto.MealDto;
import com.tripath.trifood.api.trifood.response.MealResponse;
import com.tripath.trifood.api.trifood.services.service.customReturn.FoodAmountReturnService;
import com.tripath.trifood.api.trifood.services.service.customReturn.EWeeklyScheduleReturnService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MealService {

    Long createEWeeklySchedule();

    MealDto createMeal(MealDto mealDto, Long eWeeklyScheduleId, Long eDay);

    MealDto updateMeal(MealDto mealDto, Long mealId);

    void deleteMeal(Long mealId);

    List<FoodAmountReturnService> countTotalFoodAmount(Integer weekNumber, Integer weekYear, Long eGroupId);

    List<FoodAmountDto> countTotalFoodDelete(Integer weekNumber, Integer weekYear, Long eGroupId);

    MealDto getMealById(Long mealId);

}
