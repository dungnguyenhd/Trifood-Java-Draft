package com.tripath.trifood.payloads.Dto;

import com.tripath.trifood.models.Food;
import com.tripath.trifood.models.GroupSchedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class MealDto {

    @NotNull
    private Food food;

    @NotNull
    private GroupSchedule groupSchedule;

    @NotEmpty
    private String mealName;
}
