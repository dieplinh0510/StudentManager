package com.example.studentmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "calendar")
public class Calendar {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int calendarId;
  private int type;
  private String timeStart;
  private String timeEnd;
  private String position;
  private Date datee;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "subject_id")
  private Subject subject;
}
