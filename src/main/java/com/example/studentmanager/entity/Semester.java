package com.example.studentmanager.entity;

import com.example.studentmanager.entity.base.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "semester")
public class Semester extends AbstractAuditingEntity {

  @Nationalized
  private String name;

  // subject
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "semester")
  @JsonIgnore
  private List<Subject> subjects;

  // student_semester
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "semester")
  @JsonIgnore
  private List<StudentSemester> studentSemesters;

}
