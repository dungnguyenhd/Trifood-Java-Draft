package com.tripath.trifood.api.trifood.dto;

import com.tripath.trifood.entities.GroupSchedule;
import com.tripath.trifood.entities.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class StudentOrderDto {
    @NotEmpty
    private String registerMeal;

    @NotNull
    private GroupSchedule groupSchedule;

    private Integer minusPayment;

    @NotNull
    private Student student;
}