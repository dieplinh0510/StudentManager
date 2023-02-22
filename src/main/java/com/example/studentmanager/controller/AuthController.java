package com.example.studentmanager.controller;

import com.example.studentmanager.base.RestApiV1;
import com.example.studentmanager.base.VsResponseUtil;
import com.example.studentmanager.constant.CommonConstant;
import com.example.studentmanager.constant.UrlConstant;
import com.example.studentmanager.dto.UserDTO;
import com.example.studentmanager.entity.User;
import com.example.studentmanager.events.PasswordResetCompleteEvent;
import com.example.studentmanager.events.RegistrationCompleteEvent;
import com.example.studentmanager.payload.auth.AuthenticationRequest;
import com.example.studentmanager.payload.response.AuthenticationResponse;
import com.example.studentmanager.payload.response.TrueFalseResponse;
import com.example.studentmanager.service.AuthService;
import com.example.studentmanager.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@RestApiV1
@Validated
public class AuthController {
  private final UserService userService;
  private final AuthService authService;
  private final ApplicationEventPublisher publisher;

  public AuthController(UserService userService, AuthService authService, ApplicationEventPublisher publisher) {
    this.userService = userService;
    this.authService = authService;
    this.publisher = publisher;
  }

  //sigin
  @PostMapping(UrlConstant.Auth.LOGIN)
  public ResponseEntity<?> signIn(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
    return VsResponseUtil.ok(authService.signIn(authenticationRequest));
  }

  //signup
  @PostMapping(UrlConstant.Auth.SIGNUP)
  public ResponseEntity<?> signUp(@Valid @RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest) {
    JsonNode user = authService.signup(userDTO);
    publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(httpServletRequest)));
    return VsResponseUtil.ok(new TrueFalseResponse(true));
  }

  @GetMapping(UrlConstant.Auth.VERIFY_SIGNUP)
  public ResponseEntity<?> verifyVerificationTokenSignUp(@RequestParam("token") String token) {
    return VsResponseUtil.ok(authService.validateVerificationToken(token));
  }

  //logout
  @PostMapping(UrlConstant.Auth.LOGOUT)
  public ResponseEntity<?> logoutUser(@PathVariable Long id) {
    return VsResponseUtil.ok(authService.logout(id));
  }

  //validate
  @PostMapping(UrlConstant.Auth.VALIDATE)
  public ResponseEntity<?> validateToken(@Valid @RequestBody AuthenticationResponse authenticationResponse) {
    return VsResponseUtil.ok(authService.validateToken(authenticationResponse));
  }

  //forgot password
  @PostMapping(UrlConstant.Auth.FORGOT_PASS)
  public ResponseEntity<?> generateTokenResetPass(@Email(message = "Is invalid") @RequestParam("email") String email,
                                                  final HttpServletRequest request) {
    User user = userService.getUserByEmail(email);
    publisher.publishEvent(new PasswordResetCompleteEvent(user, applicationUrl(request)));
    return VsResponseUtil.ok(user);
  }

  @PostMapping(UrlConstant.Auth.FORGOT_PASS_SUCCESS)
  public ResponseEntity<?> forgotPassword(@RequestParam(name = "email") String email,
                                          @RequestParam(name = "token") String token,
                                          @Pattern(regexp = CommonConstant.REGEX_EMAIL,
                                              message = CommonConstant.MESSAGE_ERROR_PASSWORD)
                                          @RequestParam(name = "password") String newPassword) {
    return VsResponseUtil.ok(authService.forgotPassword(email, token, newPassword));
  }

  private String applicationUrl(HttpServletRequest request) {
    return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
  }

}
