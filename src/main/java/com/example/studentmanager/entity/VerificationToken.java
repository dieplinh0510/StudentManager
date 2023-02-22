package com.example.studentmanager.entity;

import com.example.studentmanager.constant.CommonConstant;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VerificationToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String token;

  private Date expirationTime;

  @Convert(converter = JsonNodeConverter.class)
  @Column(name = "status", length = 100000)
  private JsonNode user;

  public VerificationToken(JsonNode user, String token) {
    super();
    this.token = token;
    this.user = user;
    this.expirationTime = calculateExpirationDate();
  }

  private java.util.Date calculateExpirationDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(new java.util.Date().getTime());
    calendar.add(Calendar.MINUTE, CommonConstant.EXPIRATION_TIME);
    return new java.util.Date(calendar.getTime().getTime());
  }
}
