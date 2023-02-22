package com.example.studentmanager.constant;

import org.springframework.beans.factory.annotation.Value;

public class EmailConstant {
  @Value("${mail.username}")
  public static String EMAIL_ADMIN;
  @Value("${mail.password}")
  public static String EMAIL_PASSWORD;

  public static String SUBJECT_REGISTER = "Đăng ký tài khoản";
  public static String SUBJECT_FORGOT_PASS = "Quên mật khẩu";
  public static String NOTIFICATION_ERROR = "Có bug";
}
