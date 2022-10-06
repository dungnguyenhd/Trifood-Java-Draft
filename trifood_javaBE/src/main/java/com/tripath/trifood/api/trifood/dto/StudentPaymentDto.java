package com.tripath.trifood.api.trifood.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tripath.trifood.entities.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class StudentPaymentDto {
    private Boolean isPaid;

    private Integer totalMoney;

    private LocalDateTime payDate;

    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date payStartDate;

    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date payEndDate;

    private Student student;
}
