package com.tripath.trifood.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Meal implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long mealId;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(name = "meal_name")
    private String mealName;

    @Column(name = "meal_day")
    private String mealDay;

    Boolean isCircle;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    Set<AssignSchedule> assignSchedules;
}
