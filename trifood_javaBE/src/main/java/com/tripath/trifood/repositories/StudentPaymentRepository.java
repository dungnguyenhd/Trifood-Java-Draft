package com.tripath.trifood.repositories;

import com.tripath.trifood.models.StudentPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface StudentPaymentRepository extends JpaRepository<StudentPayment, Integer> {
    @Query(value = "SELECT SUM(daily_payment - minus payment) \n" +
            "FROM students_orders o INNER JOIN eating_group_schedules s \n" +
            "ON o.e_group_schedule_id  = s.e_group_schedule_id\n" +
            "WHERE (e_group_schedule_date BETWEEN ? AND ?)", nativeQuery = true)
    public Integer getMonthlyPayment(Date startDate, Date endDate);
}
