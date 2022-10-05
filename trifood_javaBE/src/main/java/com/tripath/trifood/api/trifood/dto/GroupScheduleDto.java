package com.tripath.trifood.api.trifood.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tripath.trifood.entities.EGroup;
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
    private Date eGroupScheduleStartDate;

    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date eGroupScheduleEndDate;

    private Integer eGroupTotalPayment;

    private EGroup eGroup;
}
