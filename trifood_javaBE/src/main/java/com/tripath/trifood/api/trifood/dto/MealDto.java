package com.tripath.trifood.api.trifood.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MealDto {

    @NotNull
    private Long foodId;

    private String mealDay;

    Boolean isCircle;

    @NotEmpty
    private String mealName;
}
