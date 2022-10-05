package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.GroupSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupScheduleRespository extends JpaRepository<GroupSchedule, Integer> {
    @Query(value = "SELECT e_group_id FROM students s INNER JOIN eating_classes c ON s.e_class_e_class_id = c.e_class_id WHERE id = ?", nativeQuery = true)
    Integer findStudentGroupId(Integer studentId);

    @Query(value = "SELECT e_group_schedule_id FROM eating_group_schedules WHERE e_group_id = ?", nativeQuery = true)
    Integer findStudentGroupScheduleId(Integer groupId);

    @Query(value = "SELECT SUM(food_price) AS schedule_payment " +
            "FROM ( " +
            "SELECT food_price " +
            "FROM ((eating_group_schedules s INNER JOIN meal m ON s.e_group_schedule_id  =  m.e_group_schedule_id) " +
            "INNER JOIN food f ON m.food_id = f.food_id) WHERE (meal_date BETWEEN ? AND ?) AND e_group_id = ?) AS P", nativeQuery = true)
    Integer getTotalPayment(String startDate, String endDate, Integer groupId);

    @Query(value = "SELECT e_group_id FROM eating_groups WHERE e_group_name LIKE %?1%", nativeQuery = true)
    Integer findGroupId(String groupName);

    @Query(value = "SELECT * FROM eating_group_schedules WHERE e_group_id = ?", nativeQuery = true)
    GroupSchedule findGroupSchedule(Integer groupId);
}

