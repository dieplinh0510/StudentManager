package com.example.studentmanager.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
//@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Token {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int tokenId;
  private int value;
  private Timestamp expierTime;

  @ManyToOne(cascade = CascadeType.MERGE, fetch =  FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

}
