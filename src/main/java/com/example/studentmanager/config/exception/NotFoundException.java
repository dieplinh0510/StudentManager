package com.example.studentmanager.config.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private HttpStatus status;
  private String message;

  public NotFoundException(String message) {
    super(message);
    this.status = HttpStatus.BAD_REQUEST;
    this.message = message;
  }

  public NotFoundException(HttpStatus status, String message) {
    super(message);
    this.status = status;
    this.message = message;
  }

}
