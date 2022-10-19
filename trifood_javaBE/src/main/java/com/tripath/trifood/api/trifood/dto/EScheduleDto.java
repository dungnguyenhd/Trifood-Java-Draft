package com.tripath.trifood.api.trifood.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tripath.trifood.entities.EGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EScheduleDto implements Serializable {
    private Integer eScheduleId;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate eScheduleDate;

    private Integer eScheduleDay;

    private Integer eSchedulePayment;

    private EGroup eGroup;
}
