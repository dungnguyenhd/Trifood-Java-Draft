package com.tripath.trifood.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "e_groups")
@NoArgsConstructor
@Getter
@Setter
public class EGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eGroup_id")
    private Long eGroupId;
    @Column(name = "eGroup_name")
    private String eGroupName;
    @Column(name = "eGroup_key")
    private String eGroupKey;
    @Column(name = "eGroup_start_year")
    private int eGroupStartYear;
    @Column(name = "eGroup_end_year")
    private int eGroupEndYear;

    @OneToMany(mappedBy = "eGroup", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EClass> eClasses = new ArrayList<>();

    @OneToMany(mappedBy = "eGroup", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<WeekSchedule> weekSchedules = new ArrayList<>();
}
