package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.GroupSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupScheduleRespository extends JpaRepository<GroupSchedule, Integer> {
    @Query(value = "SELECT SUM(food_price) AS Daily_payment " +
            "FROM ( " +
            "SELECT food_price " +
            "FROM ((eating_group_schedules s INNER JOIN meal m ON s.e_group_schedule_id  =  m.e_group_schedule_id) " +
            "INNER JOIN food f ON m.food_id = f.food_id) WHERE meal_date = ? AND e_group_id = ?) AS P", nativeQuery = true)
    public Integer getTotalPayment(String scheduleDate, Integer groupId);

    @Query(value = "SELECT * FROM eating_group_schedules WHERE (meal_date BETWEEN ? AND ?) AND e_group_id = ?", nativeQuery = true)
    public List<GroupSchedule> findGroupSchedule(String startDate, String endDate, Integer groupId);
}

