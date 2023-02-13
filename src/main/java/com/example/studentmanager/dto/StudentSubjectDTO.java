package com.example.studentmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentSubjectDTO {
    private Float scoreOne;
    private Float scoreTwo;
    private Float scoreTest;
    private Boolean status;
    private Boolean active;
}
