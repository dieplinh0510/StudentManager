package com.example.studentmanager.constant;

public class CommonConstant {
  //2 phút
  public static final int EXPIRATION_TIME = 2;
  // 1 tháng / 43200 phút
  public static final int JWT_REFRESH_EXPIRATION_TIME = 43200;

  public static final String RESULT = "RESULT";

  public static final String MESSAGE_ERROR_PASSWORD = "Invalid password format. " +
      "Must be a minimum of 8 characters. " +
      "Must have a capital letter. " +
      "Must contain letters, numbers and symbols. " +
      "No spaces.";

  public static final String REGEX_EMAIL = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

}
