package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.api.trifood.services.service.customReturn.FoodAmountReturnService;
import com.tripath.trifood.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepo extends JpaRepository<Meal, Long> {
    @Query(value = "SELECT food_name as FoodName, COUNT(m.food_id) as Amount FROM (week_schedule w INNER JOIN(( meal m INNER JOIN assign_schedule s ON m.meal_id = s.meal_id)  INNER JOIN food f ON m.food_id = f.food_id) ON w.week_id = s.week_id) WHERE e_group_id = ? AND week_number = ? AND week_year = ? GROUP BY m.food_name", nativeQuery = true)
    List<FoodAmountReturnService> countTotalFoodAmount(Long eGroupId, Integer weekNumber, Integer weekYear);

    @Query(value = "SELECT food_name as FoodName, COUNT(m.food_id) as Amount FROM ((((food f INNER JOIN meal m ON f.food_id = m.food_id) INNER JOIN student_orders o ON m.meal_id = o.delete_meal) INNER JOIN students s ON s.id = o.student_id) INNER JOIN e_classes c ON s.e_class_id = c.e_class_id) WHERE order_week_month = ? AND order_week_year = ? AND c.e_group_id = ? GROUP BY food_name", nativeQuery = true)
    List<FoodAmountReturnService> countTotalFoodDelete(Integer weekNumber, Integer weekYear, Long eGroupId);
}
