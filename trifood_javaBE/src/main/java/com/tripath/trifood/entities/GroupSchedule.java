package com.tripath.trifood.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "eating_group_schedules")
@NoArgsConstructor
@Getter
@Setter
public class GroupSchedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eGroup_schedule_id")
    private Integer eGroupScheduleId;

    @Column(name = "eGroup_schedule_start_date")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date eGroupScheduleStartDate;

    @Column(name = "eGroup_schedule_end_date")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date eGroupScheduleEndDate;

    @Column(name = "eGroup_total_payment")
    private Integer eGroupTotalPayment;

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
