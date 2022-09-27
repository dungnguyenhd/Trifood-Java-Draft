package com.tripath.trifood.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "student_orders")
public class StudentOrder implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long orderId;

    private LocalDateTime registerDate;

    private String registerMeal;

    @ManyToOne
    @JoinColumn(name = "groupScheduleId")
    private GroupSchedule groupSchedule;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
}
