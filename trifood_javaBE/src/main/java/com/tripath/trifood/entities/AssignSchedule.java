package com.tripath.trifood.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class AssignSchedule implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "week_id", unique = true)
    private WeekSchedule weekSchedule;

    @ManyToOne
    @JoinColumn(name = "e_weekly_id")
    private EWeeklySchedule eWeeklySchedule;
}
