package com.tripath.trifood.api.trifood.dto;

import com.tripath.trifood.entities.Food;
import com.tripath.trifood.entities.EDailySchedule;
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
    private EDailySchedule eDailySchedule;

    @NotEmpty
    private String mealName;
}
