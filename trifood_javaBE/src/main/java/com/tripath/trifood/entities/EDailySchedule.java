package com.tripath.trifood.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "e_daily_schedule")
@NoArgsConstructor
@Getter
@Setter
public class EDailySchedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_daily_id")
    private Long eDailyId;

    private Integer eDay;

    @OneToMany(mappedBy = "eDailySchedule", cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Meal> meals;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "e_weekly_id", referencedColumnName = "e_weekly_id")
    private EWeeklySchedule eWeeklySchedule;
}
