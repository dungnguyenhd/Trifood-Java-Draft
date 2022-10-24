package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.trifood.dto.FoodAmountDto;
import com.tripath.trifood.api.trifood.exceptions.ResourceNotFoundException;
import com.tripath.trifood.api.trifood.services.service.customReturn.FoodAmountReturnService;
import com.tripath.trifood.entities.EDailySchedule;
import com.tripath.trifood.entities.EWeeklySchedule;
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
    private EDailyScheduleRepo eDailyScheduleRepo;
    @Autowired
    EWeeklyScheduleRepo weeklyRepo;
    @Autowired
    AssignScheduleRepo assignRepo;
    @Autowired
    WeekScheduleRepo weekRepo;
    @Autowired
    StudentOrderRepo orderRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Long createEWeeklySchedule() {

        EWeeklySchedule eWeeklySchedule = new EWeeklySchedule();

        for(int i = 0; i<=6; i++){
            EDailySchedule newEDailySchedule = new EDailySchedule();
            newEDailySchedule.setEDay(i);
            newEDailySchedule.setEWeeklySchedule(eWeeklySchedule);
            EDailySchedule daily = this.eDailyScheduleRepo.save(newEDailySchedule);

            switch (daily.getEDay()) {
                case 0:{
                    eWeeklySchedule.setMondaySch(daily.getEDailyId());
                    break;
                }
                case 1:{
                    eWeeklySchedule.setTuesdaySch(daily.getEDailyId());
                    break;
                }
                case 2:{
                    eWeeklySchedule.setWendnesdaySch(daily.getEDailyId());
                    break;
                }
                case 3:{
                    eWeeklySchedule.setThursdaySch(daily.getEDailyId());
                    break;
                }
                case 4:{
                    eWeeklySchedule.setFridaySch(daily.getEDailyId());
                    break;
                }
                case 5:{
                    eWeeklySchedule.setSaturdaySch(daily.getEDailyId());
                    break;
                }
                case 6:{
                    eWeeklySchedule.setSundaySch(daily.getEDailyId());
                    break;
                }
                default:{
                    break;
                }
            }
        }
        EWeeklySchedule newEWeeklySchedule = this.weeklyRepo.save(eWeeklySchedule);
        return newEWeeklySchedule.getEWeeklyId();
    }

    @Override
    public MealDto createMeal(MealDto mealDto, Long eWeeklyScheduleId, Long eDay) {
        Meal meal = this.modelMapper.map(mealDto, Meal.class);
        EDailySchedule eDailySchedule = this.eDailyScheduleRepo.findByEWeeklyIdAndEDay(eWeeklyScheduleId, eDay);
        meal.setEDailySchedule(eDailySchedule);
        Meal newMeal = this.mealRepo.save(meal);
        return this.modelMapper.map(newMeal, MealDto.class);
    }

    @Override
    public MealDto updateMeal(MealDto mealDto, Long mealId) {
        Meal meal = this.mealRepo.findById(mealId).orElseThrow(()-> new ResourceNotFoundException("Meal", "mealId", mealId));
        meal.setMealName(mealDto.getMealName());
        meal.setFood(mealDto.getFood());
        meal.setEDailySchedule(mealDto.getEDailySchedule());

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
        Long weekId = weekRepo.findWeekIdByWeekNumberOfGroup(weekNumber, weekYear, eGroupId);
        Long eWeeklyId = assignRepo.findByWeekId(weekId);
        List<FoodAmountReturnService> totalFoodAmount = this.mealRepo.countTotalFoodAmount(eWeeklyId);
        return totalFoodAmount;
    }

    @Override
    public List<FoodAmountDto> countTotalFoodDelete(Integer weekNumber, Integer weekYear, Long eGroupId) {
        Long weekId = weekRepo.findWeekIdByWeekNumberOfGroup(weekNumber, weekYear, eGroupId);
        Long eWeeklyId = assignRepo.findByWeekId(weekId);
        List<Long> listDeleteId = orderRepo.getAllDeleteMealByWeekNumber(weekNumber, weekYear);
        List<FoodAmountReturnService> listDelete = new ArrayList<>();
        listDeleteId.forEach((mId) -> {
            FoodAmountReturnService totalFoodAmount = this.mealRepo.countTotalFoodDelete(mId, eWeeklyId);
            listDelete.add(totalFoodAmount);
        });
        Map<String, Long> map = listDelete.stream().collect(groupingBy(FoodAmountReturnService::getFoodName, counting()));
        List<FoodAmountDto> newList = new ArrayList<>();
        map.entrySet()
                .stream()
                .map(e -> {
                    FoodAmountDto fa = new FoodAmountDto();
                    fa.setFoodName(e.getKey());
                    fa.setAmount(e.getValue());
                    newList.add(fa);
                    return null;
                }).collect(Collectors.toList());
        return newList;
    }

    @Override
    public MealDto getMealById(Long mealId) {
        Meal meal = this.mealRepo.findById(mealId).orElseThrow(()-> new ResourceNotFoundException("Meal", "mealId", mealId));
        return this.modelMapper.map(meal, MealDto.class);
    }

}
