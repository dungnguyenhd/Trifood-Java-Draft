package com.tripath.trifood.services.service;

import com.tripath.trifood.payloads.Dto.FoodDto;
import com.tripath.trifood.payloads.response.FoodResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface FoodService {
    //create food
    FoodDto createFood(FoodDto foodDto);

    //update food
    FoodDto updateFood(FoodDto foodDto, Integer foodId);

    //delete food
    void deleteFood(Integer foodId);

    //get all food
    FoodResponse getAllFood(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //get single  food
    FoodDto getFoodById(Integer foodId);

    //search eating food
    List<FoodDto> searchFood(String keyword);
}
