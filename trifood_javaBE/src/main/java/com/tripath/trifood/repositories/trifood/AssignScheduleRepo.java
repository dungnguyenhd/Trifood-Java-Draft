package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.api.trifood.services.service.customReturn.ScheduleReturnService;
import com.tripath.trifood.entities.AssignSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignScheduleRepo extends JpaRepository<AssignSchedule, Long> {
    @Query(value = "SELECT m.meal_id as MealId, m.meal_day as MealDay, meal_name as MealName, food_name as FoodName,  food_price as FoodPrice  FROM (week_schedule w INNER JOIN(( meal m INNER JOIN assign_schedule s ON m.meal_id = s.meal_id)  INNER JOIN food f ON m.food_id = f.food_id) ON w.week_id = s.week_id) WHERE e_group_id = ? AND week_number = ? AND week_year = ? GROUP BY m.meal_id", nativeQuery = true)
    List<ScheduleReturnService> findScheduleOfGroup(Long eGroupId, Integer weekNumber, Integer weekYear);

    @Query(value = "SELECT m.meal_id as MealId, m.meal_day as MealDay, meal_name as MealName, food_name as FoodName,  food_price as FoodPrice FROM (students h INNER JOIN (e_classes c INNER JOIN (e_groups g INNER JOIN (week_schedule w INNER JOIN(( meal m INNER JOIN assign_schedule s ON m.meal_id = s.meal_id)  INNER JOIN food f ON m.food_id = f.food_id) ON w.week_id = s.week_id) ON g.e_group_id = w.e_group_id) ON c.e_group_id = g.e_group_id) ON h.e_class_id = c.e_class_id) WHERE h.id = ? AND week_number = ? AND week_year = ? GROUP BY m.meal_id", nativeQuery = true)
    List<ScheduleReturnService> findScheduleOfStudent(Long studentId, Integer weekNumber, Integer weekYear);
}
