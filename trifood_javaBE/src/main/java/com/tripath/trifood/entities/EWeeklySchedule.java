package com.tripath.trifood.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "e_weekly_schedule")
public class EWeeklySchedule {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "e_weekly_id")
    Long eWeeklyId;

    @Column(name = "monday_sch")
    private Long mondaySch;

    @Column(name = "tuesday_sch")
    private Long tuesdaySch;

    @Column(name = "wendnes_sch")
    private Long wendnesdaySch;

    @Column(name = "thursday_sch")
    private Long thursdaySch;

    @Column(name = "friday_sch")
    private Long fridaySch;

    @Column(name = "saturday_sch")
    private Long saturdaySch;

    @Column(name = "sunday_sch")
    private Long sundaySch;

    @OneToMany(mappedBy = "eWeeklySchedule", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EDailySchedule> eDailySchedules = new ArrayList<>();
}
