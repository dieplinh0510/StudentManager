package com.example.studentmanager.entity;

import com.example.studentmanager.entity.base.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User extends AbstractAuditingEntity {
  @Id
  private String userId;

  private String userName;
  private Date dateOfBirthday;
  private String phoneNumber;
  private String email;

  @Nationalized
  private String homeTown;
  private String branch;
  private String classs;
  private int course;
  private String avatar;
  private String password;

  //role
  @ManyToOne(cascade = CascadeType.MERGE, fetch =  FetchType.EAGER)
  @JoinColumn(name = "role_id")
  private Role role;

  // token
  @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<Token> tokens;

  // notification
  @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<Notification> notifications;

  // student_semester
  @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<StudentSemester> studentSemesters;

  // student_subject
  @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<StudentSubject> studentSubjects;

  // finace
  @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<Finance> finances;
}
