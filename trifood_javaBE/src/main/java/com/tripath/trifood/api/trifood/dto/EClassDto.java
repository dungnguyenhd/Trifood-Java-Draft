package com.tripath.trifood.api.trifood.dto;

import com.tripath.trifood.entities.EGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class EClassDto {

    private Integer eClassId;

    @NotEmpty
    private String eClassName;

    @NotEmpty
    private String eClassGrade;

    @NotEmpty
    private String eClassLevel;

    @NotEmpty
    private int eClassStartYear;

    @NotEmpty
    private int eClassEndYear;

    private EGroup eGroup;
}
