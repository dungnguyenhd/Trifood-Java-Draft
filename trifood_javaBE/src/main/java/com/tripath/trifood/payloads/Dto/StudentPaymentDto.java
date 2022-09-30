package com.tripath.trifood.payloads.Dto;

import com.tripath.trifood.models.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class StudentPaymentDto {
    private Boolean isPaid;

    private Long totalMoney;

    private LocalDateTime payDate;

    private Student student;
}
