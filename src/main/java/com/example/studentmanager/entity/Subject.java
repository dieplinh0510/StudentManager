package com.example.studentmanager.entity;

import com.example.studentmanager.entity.base.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Interceptor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "subject")
public class Subject extends AbstractAuditingEntity {

  @Nationalized
  private String name;
  @Nationalized
  private String teacher;
  private String lession;
  private Integer credits;
  private Double tuition;
  private String classroom;

  private Integer numberOfStudent;

  // student_subject
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subject")
  @JsonIgnore
  private List<StudentSubject> studentSubjects;


  // calenda
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subject")
  @JsonIgnore
  private List<Calendar> calendars;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "semester_id")
  private Semester semester;

}
