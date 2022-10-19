package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.StudentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentOrderRepo extends JpaRepository<StudentOrder, Long> {
    @Query(value = "SELECT e_group_id FROM e_classes c INNER JOIN students s ON c.e_class_id = s.e_class_id WHERE id = ?", nativeQuery = true)
    Integer getStudentGroup(Integer studentId);

    @Query(value = "SELECT delete_meal FROM student_orders WHERE order_week_month = ? and order_week_year = ?", nativeQuery = true)
    List<Long> getAllDeleteMeal(Integer weekMonth, Integer weekYear);

    @Query(value = "SELECT delete_meal FROM student_orders WHERE order_week_number = ? and order_week_year = ?", nativeQuery = true)
    List<Long> getAllDeleteMealByWeekNumber(Integer weekNumber, Integer weekYear);
}
