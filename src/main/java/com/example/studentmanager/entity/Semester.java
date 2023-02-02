package com.example.studentmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "semester")
public class Semester {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int semesterId;
  private String semesterName;

  // subject
  @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "semester")
  @JsonIgnore
  private List<Subject> subjects;

  // student_semester
  @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "semester")
  @JsonIgnore
  private List<StudentSemester> studentSemesters;
}
