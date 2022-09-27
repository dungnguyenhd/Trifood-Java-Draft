package com.tripath.trifood.payloads.Dto;

import com.tripath.trifood.models.GroupSchedule;
import com.tripath.trifood.models.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class StudentOrderDto {

    private LocalDateTime registerDate;

    @NotEmpty
    private String registerMeal;

    @NotNull
    private GroupSchedule groupSchedule;

    @NotNull
    private Student student;
}
