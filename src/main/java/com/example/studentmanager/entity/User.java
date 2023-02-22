package com.example.studentmanager.entity;

import com.example.studentmanager.constant.AuthenticationProvider;
import com.example.studentmanager.entity.base.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User extends AbstractAuditingEntity implements Serializable {

  @Nationalized
  private String name;
  private Date dateOfBirthday;
  private String phone;
  @Email
  @Column(unique = true)
  private String email;
  @JsonIgnore
  private String password;
  @Nationalized
  private String homeTown;
  private String branch;
  private String classs;
  private Integer course;
  private String avatar;
  @Nationalized
  private String gender;

  @Enumerated(EnumType.STRING)
  private AuthenticationProvider authProvider;
  //link to table Role

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "role_id")
  private Role role;

  // notification
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<Notification> notifications;

  // student_semester
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<StudentSemester> studentSemesters;

  // student_subject
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<StudentSubject> studentSubjects;

  // finace
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  @JsonIgnore
  private List<Finance> finances;

}
