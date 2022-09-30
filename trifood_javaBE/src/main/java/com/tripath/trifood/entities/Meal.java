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
public class Meal implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "eGroup_schedule_id")
    private GroupSchedule groupSchedule;

    @Column(name = "meal_name")
    private String mealName;
}
