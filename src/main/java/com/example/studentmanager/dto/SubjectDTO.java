package com.example.studentmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    @Nationalized
    private String name;
    @Nationalized
    private String teacher;
    private String lession;
    private Integer credits;
    private Double tuition;
    private String classroom;
    private Integer numberOfStudent;
}
