package com.tripath.trifood.payloads.Dto;

import com.tripath.trifood.models.EGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class GroupScheduleDto {
    private Integer eGroupScheduleId;

    @NotEmpty
    @Min(value = 2, message = "Day must be from 2 to 8 (Monday to Sunday)")
    @Max(value = 8, message = "Day must be from 2 to 8 (Monday to Sunday)")
    private Integer eGroupScheduleDay;

    @NotEmpty
    private Integer eGroupScheduleStartYear;

    @NotEmpty
    private Integer eGroupScheduleEndYear;

    private EGroup eGroup;
}
