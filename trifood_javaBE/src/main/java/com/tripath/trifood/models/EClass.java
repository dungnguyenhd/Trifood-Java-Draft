package com.tripath.trifood.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="eating_classes")
@NoArgsConstructor
@Getter
@Setter
public class EClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eClassId;
    @Column(name = "eClass_name")
    private String eClassName;
    @Column(name = "eClass_grade")
    private String eClassGrade;
    @Column(name = "eClass_level")
    private String eClassLevel;
    @Column(name = "eClass_start_year", length = 4)
    private int eClassStartYear;
    @Column(name = "eClass_end_year", length = 4)
    private int eClassEndYear;

    @OneToMany(mappedBy = "eClass", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "eGroup_id", referencedColumnName = "eGroup_id")
    private EGroup eGroup;
}
