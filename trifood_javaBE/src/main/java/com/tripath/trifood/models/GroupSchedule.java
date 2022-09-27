package com.tripath.trifood.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "eating_group_schedules")
@NoArgsConstructor
@Getter
@Setter
public class GroupSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eGroup_schedule_id")
    private Integer eGroupScheduleId;

    @Column(name = "eGroup_schedule_day", length = 1)
    private Integer eGroupScheduleDay;

    @Column(name = "eGroup_schedule_start_year", length = 4)
    private Integer eGroupScheduleStartYear;

    @Column(name = "eGroup_schedule_end_year", length = 4)
    private Integer eGroupScheduleEndYear;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "eGroup_id", referencedColumnName = "eGroup_id")
    private EGroup eGroup;

    @OneToMany(mappedBy = "groupSchedule", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Meal> meals;

    @OneToMany(mappedBy = "groupSchedule", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<StudentOrder> studentOrders;
}
