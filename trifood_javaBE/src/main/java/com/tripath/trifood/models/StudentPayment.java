package com.tripath.trifood.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_payments")
@NoArgsConstructor
@Getter
@Setter
public class StudentPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer payment_id;

    private Boolean is_paid;

    private LocalDateTime pay_date;
}
