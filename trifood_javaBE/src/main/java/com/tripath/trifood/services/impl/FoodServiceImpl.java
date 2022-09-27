package com.tripath.trifood.services.impl;

import com.tripath.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.models.Food;
import com.tripath.trifood.payloads.Dto.FoodDto;
import com.tripath.trifood.payloads.response.FoodResponse;
import com.tripath.trifood.repositories.FoodRepository;
import com.tripath.trifood.services.service.FoodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FoodDto createFood(FoodDto foodDto) {
        Food food = this.modelMapper.map(foodDto, Food.class);
        Food newFood = this.foodRepo.save(food);
        return this.modelMapper.map(newFood, FoodDto.class);
    }

    @Override
    public FoodDto updateFood(FoodDto foodDto, Integer foodId) {
        Food food = this.foodRepo.findById(foodId).orElseThrow(()-> new ResourceNotFoundException("Food", "foodId", foodId));
        food.setFoodName(foodDto.getFoodName());
        food.setFoodPrice(foodDto.getFoodPrice());
        food.setFoodCalculationUnit(foodDto.getFoodCalculationUnit());

        Food updatedFood = this.foodRepo.save(food);
        return this.modelMapper.map(updatedFood, FoodDto.class);
    }

    @Override
    public void deleteFood(Integer foodId) {
        Food food = this.foodRepo.findById(foodId).orElseThrow(()-> new ResourceNotFoundException("Food", "foodId", foodId));
        this.foodRepo.delete(food);
    }

    @Override
    public FoodResponse getAllFood(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Food> pageFood = this.foodRepo.findAll(p);
        List<Food> allFood = pageFood.getContent();
        List<FoodDto> foodDtos = allFood.stream().map((food) -> this.modelMapper.map(food, FoodDto.class)).collect(Collectors.toList());

        FoodResponse foodResponse = new FoodResponse();
        foodResponse.setContent(foodDtos);
        foodResponse.setPageNumber(pageFood.getNumber());
        foodResponse.setTotalElements(pageFood.getTotalElements());
        foodResponse.setTotalPages(pageFood.getTotalPages());
        foodResponse.setLastPage(pageFood.isLast());

        return foodResponse;
    }

    @Override
    public FoodDto getFoodById(Integer foodId) {
        Food food = this.foodRepo.findById(foodId).orElseThrow(()-> new ResourceNotFoundException("Food", "foodId", foodId));
        return this.modelMapper.map(food, FoodDto.class);
    }

    @Override
    public List<FoodDto> searchFood(String keyword) {
        List<Food> foods = this.foodRepo.searchByName("%"+keyword+"%");
        List<FoodDto> foodDtos = foods.stream().map((food)->this.modelMapper.map(food, FoodDto.class)).collect(Collectors.toList());
        return foodDtos;
    }
}
