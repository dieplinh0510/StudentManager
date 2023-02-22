package com.example.studentmanager.payload.response;

import com.example.studentmanager.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
  private String token;
  private String tokenType = "Bearer";
  private String refreshToken;
  private User user;

  public AuthenticationResponse(String token, String refreshToken, User user) {
    this.token = token;
    this.refreshToken = refreshToken;
    this.user = user;
  }

}
