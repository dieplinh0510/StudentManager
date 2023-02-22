package com.example.studentmanager.config.exception;

public class TokenRefreshException extends RuntimeException {
  public TokenRefreshException(String token, String message) {
    super(String.format("Faile for [%s] : %s", token, message));
  }
}
