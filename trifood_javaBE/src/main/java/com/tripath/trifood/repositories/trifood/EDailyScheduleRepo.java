package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.EDailySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EDailyScheduleRepo extends JpaRepository<EDailySchedule, Long> {
    @Query(value = "SELECT e_group_id FROM students s INNER JOIN e_classes c ON s.e_class_id = c.e_class_id WHERE id = ?", nativeQuery = true)
    Long findStudentGroupId(Integer studentId);

    @Query(value = "SELECT * FROM e_daily_schedule WHERE e_weekly_id = ? AND e_day = ?", nativeQuery = true)
    EDailySchedule findByEWeeklyIdAndEDay(Long eWeeklyId, Long eDay);
}

