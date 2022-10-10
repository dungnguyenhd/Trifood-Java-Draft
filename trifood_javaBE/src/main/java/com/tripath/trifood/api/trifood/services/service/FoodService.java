package com.tripath.trifood.api.trifood.services.service;

import com.tripath.trifood.api.trifood.dto.FoodDto;
import com.tripath.trifood.api.trifood.response.FoodResponse;
import org.springframework.stereotype.Service;

@Service
public interface FoodService {
    FoodDto createFood(FoodDto foodDto);

    FoodDto updateFood(FoodDto foodDto, Integer foodId);

    void deleteFood(Integer foodId);

    FoodResponse getAllFood(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    FoodDto getFoodById(Integer foodId);

    FoodResponse searchFood(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
}
