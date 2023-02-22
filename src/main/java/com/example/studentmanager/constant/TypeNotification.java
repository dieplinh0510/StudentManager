package com.example.studentmanager.constant;

public enum TypeNotification {
  SCHOLARSHIP("SCHOLARSHIP", 1), LEARNMORE("LEARNMORE", 2), DEBT("DEBT", 3),
  SUCCESSFULREGISTRATION("SUCCESSFUL REGISTRATION", 4);

  private final String key;
  private final Integer value;

  TypeNotification(String key, Integer value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public Integer getValue() {
    return value;
  }
}
