package com.example.studentmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "subject")
public class Subject {
  @Id
  private String subjectId;

  @Nationalized
  private String subjectName;
  @Nationalized
  private String teacher;
  private String lession;
  private int credits;
  private double tuition;
  private String classroom;
  private int semesterId;

  // student_subject
  @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subject")
  @JsonIgnore
  private List<StudentSubject> studentSubjects;


  // calenda
  @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subject")
  @JsonIgnore
  private List<Calendar> calendars;

  @ManyToOne(cascade = CascadeType.MERGE, fetch =  FetchType.EAGER)
  @JoinColumn(name = "semester_id")
  private Semester semester;

}
