package com.tripath.trifood.payloads.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripath.trifood.models.EGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class GroupScheduleDto implements Serializable {
    private Integer eGroupScheduleId;

    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date eGroupScheduleDate;

    @NotEmpty
    private int eGroupScheduleDay;

    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date eGroupScheduleStartDate;

    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date eGroupScheduleEndDate;

    private Integer eGroupDailyPayment;

    private EGroup eGroup;
}
