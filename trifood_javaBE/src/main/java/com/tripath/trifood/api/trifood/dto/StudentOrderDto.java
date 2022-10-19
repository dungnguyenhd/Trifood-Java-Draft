package com.tripath.trifood.api.trifood.dto;

import com.tripath.trifood.api.student.dto.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class StudentOrderDto {
    private Long deleteMeal;

    private Integer orderWeekNumber;

    private Integer orderWeekMonth;

    private Integer orderWeekYear;

    @NotNull
    private Student student;
}
