package com.example.studentmanager.service;

import com.example.studentmanager.dto.UserDTO;
import com.example.studentmanager.entity.User;
import com.example.studentmanager.payload.auth.AuthenticationRequest;
import com.example.studentmanager.payload.response.AuthenticationResponse;
import com.example.studentmanager.payload.response.TrueFalseResponse;
import com.fasterxml.jackson.databind.JsonNode;

public interface AuthService {
  //dang nhap
  AuthenticationResponse signIn(AuthenticationRequest request);

  //dang ki
  JsonNode signup(UserDTO userDTO);

  void saveVerificationTokenForUser(JsonNode user, String token);

  //xac nhan dang ki
  TrueFalseResponse validateVerificationToken(String token);

  //luu token xac nhan quen mat khau
  void savePasswordResetToken(String token, User user);

  //xac nhan doi
  TrueFalseResponse forgotPassword(String email, String token, String newPassword);

  //kiem tra token dang nhap
  TrueFalseResponse validateToken(AuthenticationResponse authenticationResponse);

  //dang xuat
  TrueFalseResponse logout(Long id);


}
