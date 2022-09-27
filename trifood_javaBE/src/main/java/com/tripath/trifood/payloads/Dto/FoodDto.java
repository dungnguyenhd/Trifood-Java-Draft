package com.tripath.trifood.payloads.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
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
