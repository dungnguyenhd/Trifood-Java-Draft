package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.StudentPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentPaymentRepository extends JpaRepository<StudentPayment, Long> {
    @Query(value = "SELECT SUM(e_group_daily_payment - minus_payment) AS total_payment " +
            "FROM ((student_orders o INNER JOIN eating_group_schedules s " +
            "ON o.group_schedule_id  = s.e_group_schedule_id) INNER JOIN meal m ON s.e_group_schedule_id = m.e_group_schedule_id) " +
            "WHERE (meal_date BETWEEN ? AND ?) AND student_id = ?", nativeQuery = true)
    public Integer getMonthlyPayment(String startDate, String endDate, Integer studentId);
}
