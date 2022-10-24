package com.tripath.trifood.entities;

import com.tripath.trifood.api.student.dto.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "student_payments")
@NoArgsConstructor
@Getter
@Setter
public class StudentPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Integer totalMoney;

    private Boolean isPaid;

    private Integer payMonth;

    private Long foodAmount;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
}
