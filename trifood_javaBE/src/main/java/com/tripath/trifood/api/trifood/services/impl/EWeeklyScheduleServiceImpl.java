package com.tripath.trifood.api.trifood.services.impl;

import com.tripath.trifood.api.trifood.services.service.EWeeklyScheduleService;
import com.tripath.trifood.api.trifood.services.service.customReturn.EWeeklyScheduleReturnService;
import com.tripath.trifood.entities.EWeeklySchedule;
import com.tripath.trifood.repositories.trifood.AssignScheduleRepo;
import com.tripath.trifood.repositories.trifood.EGroupRepo;
import com.tripath.trifood.repositories.trifood.EWeeklyScheduleRepo;
import com.tripath.trifood.repositories.trifood.WeekScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EWeeklyScheduleServiceImpl implements EWeeklyScheduleService {
    @Autowired
    private WeekScheduleRepo weekRepo;
    @Autowired
    private EWeeklyScheduleRepo weeklyRepo;
    @Autowired
    private AssignScheduleRepo assignRepo;
    @Autowired
    private EGroupRepo eGroupRepo;

    @Override
    public List<EWeeklyScheduleReturnService> findEWeeklyScheduleByGroup(Long eGroupId, Integer weekNumber){
        Long weekId = weekRepo.findByGroupAndWeekNumber(eGroupId, weekNumber);
        Long eWeeklyId = assignRepo.findByWeekId(weekId);
        EWeeklySchedule eWeeklySchedule = weeklyRepo.findByEWeeklyId(eWeeklyId);

        Long mondaySch = eWeeklySchedule.getMondaySch();
        Long tuesdaySch = eWeeklySchedule.getTuesdaySch();
        Long wendnesdaySch = eWeeklySchedule.getWendnesdaySch();
        Long thursdaySch = eWeeklySchedule.getThursdaySch();
        Long fridaySch = eWeeklySchedule.getFridaySch();
        Long saturdaySch = eWeeklySchedule.getSaturdaySch();
        Long sundaySch = eWeeklySchedule.getSundaySch();

        List<EWeeklyScheduleReturnService> result0 = weeklyRepo.findByDailyAndWeeklyId(mondaySch, eWeeklyId);
        List<EWeeklyScheduleReturnService> result1 = weeklyRepo.findByDailyAndWeeklyId(tuesdaySch, eWeeklyId);
        List<EWeeklyScheduleReturnService> result2 = weeklyRepo.findByDailyAndWeeklyId(wendnesdaySch, eWeeklyId);
        List<EWeeklyScheduleReturnService> result3 = weeklyRepo.findByDailyAndWeeklyId(thursdaySch, eWeeklyId);
        List<EWeeklyScheduleReturnService> result4 = weeklyRepo.findByDailyAndWeeklyId(fridaySch, eWeeklyId);
        List<EWeeklyScheduleReturnService> result5 = weeklyRepo.findByDailyAndWeeklyId(saturdaySch, eWeeklyId);
        List<EWeeklyScheduleReturnService> result6 = weeklyRepo.findByDailyAndWeeklyId(sundaySch, eWeeklyId);

        List<EWeeklyScheduleReturnService> newList = Stream.of(result0, result1, result2, result3, result4, result5, result6).flatMap(Collection::stream).collect(Collectors.toList());

        return newList;
    }

    @Override
    public List<EWeeklyScheduleReturnService> findEWeeklyScheduleByStudent(Long studentId, Integer weekNumber) {
        Long groupId = eGroupRepo.findByStudent(studentId);
        List<EWeeklyScheduleReturnService> newList = findEWeeklyScheduleByGroup(groupId, weekNumber);
        return newList;
    }
}
