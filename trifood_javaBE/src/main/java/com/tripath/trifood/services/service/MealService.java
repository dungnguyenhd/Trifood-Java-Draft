package com.tripath.trifood.services.service;

import com.tripath.trifood.payloads.Dto.MealDto;
import com.tripath.trifood.payloads.response.MealResponse;
import org.springframework.stereotype.Service;


@Service
public interface MealService {
    //create meal
    MealDto createMeal(MealDto mealDto);

    //update meal
    MealDto updateMeal(MealDto mealDto, Long mealId);

    //delete meal
    void deleteMeal(Long mealId);

    //get all meal
    MealResponse getAllMeal(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get single  meal
    MealDto getMealById(Long mealId);

}
