package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.dto.MealDto;
import com.tripath.trifood.api.trifood.response.MealResponse;
import org.springframework.stereotype.Service;


@Service
public interface MealService {

    MealDto createMeal(MealDto mealDto);

    MealDto updateMeal(MealDto mealDto, Long mealId);

    void deleteMeal(Long mealId);

    MealResponse getAllMeal(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    MealDto getMealById(Long mealId);

}
