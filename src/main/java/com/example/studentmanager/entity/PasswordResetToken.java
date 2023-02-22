package com.example.studentmanager.entity;

import com.example.studentmanager.constant.CommonConstant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class PasswordResetToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String token;
  private Date expirationTime;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_PASSWORD_TOKEN"))
  private User user;

  public PasswordResetToken(User user, String token) {
    super();
    this.token = token;
    this.user = user;
    this.expirationTime = calculateExpirationDate();
  }

  private Date calculateExpirationDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(new Date().getTime());
    calendar.add(Calendar.MINUTE, CommonConstant.EXPIRATION_TIME);
    return new Date(calendar.getTime().getTime());
  }
}
