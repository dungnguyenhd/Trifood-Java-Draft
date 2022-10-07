package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.api.trifood.services.service.FoodAmountReturnService;
import com.tripath.trifood.api.trifood.services.service.ScheduleReturnService;
import com.tripath.trifood.entities.Meal;
import com.tripath.trifood.api.trifood.dto.MealDto;
import com.tripath.trifood.api.trifood.response.MealResponse;
import com.tripath.trifood.repositories.trifood.MealRepository;
import com.tripath.trifood.api.trifood.services.service.MealService;
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
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository mealRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MealDto createMeal(MealDto mealDto) {
        Meal meal = this.modelMapper.map(mealDto, Meal.class);
        meal.setMealDay(meal.getMealDate().getDay());
        Meal newEgroup = this.mealRepo.save(meal);
        return this.modelMapper.map(newEgroup, MealDto.class);
    }

    @Override
    public MealDto updateMeal(MealDto mealDto, Long mealId) {
        Meal meal = this.mealRepo.findById(mealId).orElseThrow(()-> new ResourceNotFoundException("Meal", "mealId", mealId));
        meal.setMealName(mealDto.getMealName());
        meal.setFood(mealDto.getFood());
        meal.setGroupSchedule(mealDto.getGroupSchedule());

        Meal updatedMeal = this.mealRepo.save(meal);
        return this.modelMapper.map(updatedMeal, MealDto.class);
    }

    @Override
    public void deleteMeal(Long mealId) {
        Meal meal = this.mealRepo.findById(mealId).orElseThrow(()-> new ResourceNotFoundException("Meal", "mealId", mealId));
        this.mealRepo.delete(meal);
    }

    @Override
    public MealResponse getAllMeal(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Meal> pageMeal = this.mealRepo.findAll(p);
        List<Meal> allMeal = pageMeal.getContent();
        List<MealDto> mealDtos = allMeal.stream().map((meal) -> this.modelMapper.map(meal, MealDto.class)).collect(Collectors.toList());

        MealResponse mealResponse = new MealResponse();
        mealResponse.setContent(mealDtos);
        mealResponse.setPageNumber(pageMeal.getNumber());
        mealResponse.setTotalElements(pageMeal.getTotalElements());
        mealResponse.setTotalPages(pageMeal.getTotalPages());
        mealResponse.setLastPage(pageMeal.isLast());

        return mealResponse;
    }

    @Override
    public List<ScheduleReturnService> getMealFood(String startDate, String endDate, Integer groupScheduleId) {
        try {
            List<ScheduleReturnService> result = this.mealRepo.getMealFood(startDate, endDate, groupScheduleId);
                return result;
        }
        catch (ResourceNotFoundException exception){
            throw new ResourceNotFoundException("Food", "groupScheduleId", groupScheduleId, startDate, endDate);
        }
    }

    @Override
    public List<FoodAmountReturnService> countTotalFoodAmount(String startDate, String endDate) {
        try {
            List<FoodAmountReturnService> totalFoodAmount = this.mealRepo.countTotalFoodAmount(startDate, endDate);
            return totalFoodAmount;
        }
        catch (ResourceNotFoundException exception){
            throw new ResourceNotFoundException("Amount", "Date", startDate, endDate);
        }
    }

    @Override
    public MealDto getMealById(Long mealId) {
        Meal meal = this.mealRepo.findById(mealId).orElseThrow(()-> new ResourceNotFoundException("Meal", "mealId", mealId));
        return this.modelMapper.map(meal, MealDto.class);
    }

}
