package com.example.studentmanager.payload.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
  @NotBlank(message = "Must not be blank")
  private String emailOrPhone;

  @NotBlank(message = "Must not be blank")
  private String password;
}
