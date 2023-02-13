package com.example.studentmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Nationalized
    private String name;
    private Date dateOfBirthday;
    private String phoneNumber;
    private String email;
    private String password;
    @Nationalized
    private String homeTown;
    private String branch;
    private String classs;
    private Integer course;
    private String avatar;
}
