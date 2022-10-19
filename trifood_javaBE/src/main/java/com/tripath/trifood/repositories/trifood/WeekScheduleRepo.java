package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.entities.WeekSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeekScheduleRepo extends JpaRepository<WeekSchedule, Long> {
    @Query(value = "SELECT week_id FROM week_schedule WHERE e_group_id = ?", nativeQuery = true)
    List<Long> getWeekIdByEGroupId(Long eGroupId);

    @Query(value = "SELECT week_id FROM week_schedule WHERE e_group_id = ? AND week_number = ?", nativeQuery = true)
    Long findByGroupAndWeekNumber(Long eGroupId, Integer weekNumber);

    @Query(value = "SELECT week_id FROM week_schedule WHERE e_group_id = ? AND week_number AND week_year", nativeQuery = true)
    Long getSingleWeekIdByEGroupId(Long eGroupId, Integer weekNumber, Integer weekYear);

    @Query(value = "SELECT week_id FROM week_schedule WHERE week_month = ? and week_year = ?", nativeQuery = true)
    List<Long> findWeekIdByMonth(Integer weekMonth, Integer weekYear);

    @Query(value = "SELECT week_id FROM week_schedule WHERE week_number = ? AND week_year = ? AND e_group_id = ?", nativeQuery = true)
    Long findWeekIdByWeekNumberOfGroup(Integer weekMonth, Integer weekYear, Long eGroupId);
}
