package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.api.trifood.services.service.ScheduleReturnService;
import com.tripath.trifood.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query(value = "SELECT m.meal_day as scheduleDay, m.meal_name as MealName ,f.food_name as FoodName " +
            "FROM ((eating_group_schedules s INNER JOIN meal m ON s.e_group_schedule_id  =  m.e_group_schedule_id) " +
            "INNER JOIN food f ON m.food_id = f.food_id) " +
            "WHERE (meal_date BETWEEN ? AND ?)", nativeQuery = true)
    public List<ScheduleReturnService> getMealFood(String startDate, String endDate);
}
