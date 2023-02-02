package com.example.studentmanager.entity;

import com.example.studentmanager.entity.base.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "student_subject")
public class StudentSubject{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int studentSubjectId;
  private float scoreOne;
  private float scoreTwo;
  private float scoreTest;
  private int number;
  private boolean status;
  private boolean active;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "subject_id")
  private Subject subject;
}
