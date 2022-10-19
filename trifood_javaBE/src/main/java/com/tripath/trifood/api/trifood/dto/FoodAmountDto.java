package com.tripath.trifood.api.trifood.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FoodAmountDto {
    String foodName;

    Long amount;
}
