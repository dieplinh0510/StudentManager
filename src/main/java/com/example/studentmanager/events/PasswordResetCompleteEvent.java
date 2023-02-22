package com.example.studentmanager.events;

import com.example.studentmanager.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class PasswordResetCompleteEvent extends ApplicationEvent {
  private User user;
  private String applicationUrl;

  public PasswordResetCompleteEvent(User user, String applicationUrl) {
    super(user);
    this.user = user;
    this.applicationUrl = applicationUrl;
  }
}