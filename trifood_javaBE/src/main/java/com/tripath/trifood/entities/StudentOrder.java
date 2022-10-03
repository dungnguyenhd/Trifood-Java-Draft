package com.tripath.trifood.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "student_orders")
public class StudentOrder implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long orderId;

    private Integer minusPayment;

    private String registerMeal;

    @Column(name = "order_date")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "groupScheduleId")
    private GroupSchedule groupSchedule;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
}
