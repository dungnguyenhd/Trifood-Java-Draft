package com.tripath.trifood.api.trifood.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class FoodDto {
    private Integer foodId;

    @NotEmpty
    private String foodName;

    @NotEmpty
    private Long foodPrice;

    @NotEmpty
    private String foodCalculationUnit;
}
