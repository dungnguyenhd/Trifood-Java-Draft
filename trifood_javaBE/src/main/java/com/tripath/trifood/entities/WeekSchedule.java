package com.tripath.trifood.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class WeekSchedule implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long weekId;

    private Integer weekNumber;

    private Integer weekMonth;

    private Integer weekYear;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "eGroup_id", referencedColumnName = "eGroup_id")
    private EGroup eGroup;
}
