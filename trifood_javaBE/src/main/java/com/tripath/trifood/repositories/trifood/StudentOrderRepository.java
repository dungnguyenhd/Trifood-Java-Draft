package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.StudentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentOrderRepository extends JpaRepository<StudentOrder, Long> {
    @Query(value = "SELECT e_group_id FROM eating_classes c INNER JOIN students s ON c.e_class_id = s.e_class_e_class_id WHERE id = ?", nativeQuery = true)
    Integer getStudentGroup(Integer studentId);

    @Query(value = "SELECT SUM(food_price) AS meal_payment FROM ( SELECT food_price FROM ((eating_group_schedules s INNER JOIN meal m ON s.e_group_schedule_id  =  m.e_group_schedule_id) \n" +
            "INNER JOIN food f ON m.food_id = f.food_id) WHERE meal_name = ? AND e_group_id = ? AND meal_date = ?) AS P", nativeQuery = true)
    Integer getMinusPayment(String meal_name, Integer groupId, String mealDate);
}
