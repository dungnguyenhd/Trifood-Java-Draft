package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.AssignSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignScheduleRepo extends JpaRepository<AssignSchedule, Long> {
    @Query(value = "SELECT * FROM assign_schedule WHERE week_id = ? AND e_weekly_id = ?",nativeQuery = true)
    AssignSchedule findByWeekIdAndEWeeklyId(Long weekId, Long eWeeklyId);

    @Query(value = "SELECT e_weekly_id FROM assign_schedule WHERE week_id = ?", nativeQuery = true)
    Long findByWeekId(Long weekId);
}
