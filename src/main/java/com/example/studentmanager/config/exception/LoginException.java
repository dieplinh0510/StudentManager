package com.example.studentmanager.config.exception;

public class LoginException extends RuntimeException {
  public LoginException(String message) {
    super(message);
  }
}