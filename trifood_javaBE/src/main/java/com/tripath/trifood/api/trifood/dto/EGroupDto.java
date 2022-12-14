package com.tripath.trifood.api.trifood.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class EGroupDto {

    private Integer eGroupId;

    @NotEmpty
    private String eGroupKey;

    @NotEmpty
    private String eGroupName;

    @NotEmpty
    private int eGroupStartYear;

    @NotEmpty
    private int eGroupEndYear;
}
