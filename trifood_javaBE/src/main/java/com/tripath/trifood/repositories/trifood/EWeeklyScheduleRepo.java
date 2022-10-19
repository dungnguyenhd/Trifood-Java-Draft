package com.tripath.trifood.repositories.trifood;

import com.tripath.trifood.api.trifood.services.service.customReturn.EWeeklyScheduleReturnService;
import com.tripath.trifood.entities.EWeeklySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EWeeklyScheduleRepo extends JpaRepository<EWeeklySchedule, Long> {
    @Query(value = "SELECT * FROM e_weekly_schedule WHERE e_weekly_id = ?",nativeQuery = true)
    EWeeklySchedule findByEWeeklyId(Long eWeeklyId);

    @Query(value = "SELECT e_weekly_id FROM assign_schedule WHERE week_id = ?", nativeQuery = true)
    Long findAllByWeekId(Long weekId);

    @Query(value = "SELECT m.id, e_day as EDay, meal_name as MealName, food_name as FoodName, food_price as FoodPrice FROM (( e_daily_schedule d INNER JOIN meal m ON d.e_daily_id = m. e_daily_id) INNER JOIN food f ON m.food_id = f.food_id) WHERE d.e_daily_id  = ?  AND d.e_weekly_id = ? GROUP BY m.id", nativeQuery = true)
    List<EWeeklyScheduleReturnService> findByDailyAndWeeklyId(Long eDayilyId, Long EWeeklyId);
}
