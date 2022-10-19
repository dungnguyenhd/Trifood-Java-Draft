package com.tripath.trifood.entities;

import com.tripath.trifood.api.student.dto.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "student_orders")
public class StudentOrder implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long orderId;

    private Long deleteMeal;

    private Integer orderWeekNumber;

    private Integer orderWeekMonth;

    private Integer orderWeekYear;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
}
