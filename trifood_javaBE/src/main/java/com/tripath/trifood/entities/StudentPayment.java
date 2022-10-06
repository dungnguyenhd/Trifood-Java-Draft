package com.tripath.trifood.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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

    private LocalDateTime payDate;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date payStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date payEndDate;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
}
