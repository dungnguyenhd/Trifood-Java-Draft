package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.StudentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface StudentOrderRepository extends JpaRepository<StudentOrder, Long> {
    @Query(value = "SELECT e_group_id FROM eating_classes c INNER JOIN students s ON c.e_class_id = s.e_class_e_class_id WHERE id = ?", nativeQuery = true)
    public Integer getStudentGroup(Integer studentId);

    @Query(value = "SELECT SUM(food_price) AS meal_payment  \n" +
            "FROM (\n" +
            "SELECT food_price\n" +
            "FROM ((eating_group_schedules s INNER JOIN meal m ON s.e_group_schedule_id  =  m.e_group_schedule_id) \n" +
            "INNER JOIN food f ON m.food_id = f.food_id) \n" +
            "WHERE meal_name = ? AND e_group_id = ? AND meal_date = ?) AS P", nativeQuery = true)
    public Integer getMinusPayment(String meal_name, Integer groupId, String mealDate);
}
