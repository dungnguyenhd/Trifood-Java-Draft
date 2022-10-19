package com.tripath.trifood.api.trifood.dto;

import com.tripath.trifood.api.student.dto.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class StudentPaymentDto {
    private Boolean isPaid;

    private Integer totalMoney;

    private LocalDateTime payMonth;

    private Student student;
}
