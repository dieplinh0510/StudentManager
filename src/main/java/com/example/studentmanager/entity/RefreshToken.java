package com.example.studentmanager.entity;


import com.example.studentmanager.constant.CommonConstant;
import com.example.studentmanager.entity.base.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
@Entity
@AllArgsConstructor
public class RefreshToken extends AbstractAuditingEntity {
  @Column(nullable = false, unique = true)
  private String token;
  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_REFRESH_TOKEN_USER"))
  private User user;

  @Column(nullable = false)
  private Date expirationTime;

  public RefreshToken(String token, User user) {
    super();
    this.token = token;
    this.user = user;
    this.expirationTime = calculateExpirationDate();
  }

  private Date calculateExpirationDate() {
    java.util.Calendar calendar = java.util.Calendar.getInstance();
    calendar.setTimeInMillis(new Date().getTime());
    calendar.add(Calendar.MINUTE, CommonConstant.JWT_REFRESH_EXPIRATION_TIME);
    return new Date(calendar.getTime().getTime());
  }

}
