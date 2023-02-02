package com.example.studentmanager.entity;

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
@Table(name = "notifications")
public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int notificationId;
  private String subject;
  private String content;
  private boolean isRead;
  private int type;


  @ManyToOne(cascade = CascadeType.MERGE, fetch =  FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

}
