package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.StudentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentOrderRepo extends JpaRepository<StudentOrder, Long> {
    @Query(value = "SELECT SUM(food_price) FROM ((meal m INNER JOIN food f ON m.food_id = f.food_id) INNER JOIN student_orders o ON o.delete_meal = m.meal_id) WHERE order_week_month = ? and order_week_year = ? and o.student_id = ?", nativeQuery = true)
    Integer getDeleteMealMinusPayment(Integer weekMonth, Integer weekYear, Long studentId);

    @Query(value = "SELECT COUNT(delete_meal) FROM student_orders WHERE student_id = ? and order_week_month = ? and order_week_year = ?", nativeQuery = true)
    Integer getAllMonthlyDeleteMeal(Long studentId ,Integer weekNumber, Integer weekYear);
}
