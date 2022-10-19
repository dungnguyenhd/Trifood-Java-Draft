package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.api.trifood.services.service.customReturn.FoodAmountReturnService;
import com.tripath.trifood.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepo extends JpaRepository<Meal, Long> {
    @Query(value = "SELECT m.id FROM (meal m INNER JOIN e_daily_schedule d ON m.e_daily_id = d.e_daily_id) WHERE e_weekly_id = ?", nativeQuery = true)
    List<Long> findAllMealIdBySchedule(Long weeklyId);

    @Query(value = "SELECT food_name as FoodName, COUNT(m.food_id) as Amount " +
            "FROM ((food f INNER JOIN meal m ON f.food_id = m.food_id) INNER JOIN e_daily_schedule d ON m.e_daily_id = d.e_daily_id)" +
            "WHERE e_weekly_id = ? GROUP BY food_name", nativeQuery = true)
    List<FoodAmountReturnService> countTotalFoodAmount(Long eWeeklyId);

    @Query(value = "SELECT food_name as FoodName, COUNT(m.food_id) as Amount " +
            "FROM ((food f INNER JOIN meal m ON f.food_id = m.food_id) INNER JOIN e_daily_schedule d ON m.e_daily_id = d.e_daily_id)" +
            "WHERE m.id = ? AND e_weekly_id = ? GROUP BY food_name", nativeQuery = true)
    FoodAmountReturnService countTotalFoodDelete(Long mealId, Long eWeeklyId);
}
