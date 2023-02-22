package com.example.studentmanager.events.listener;

import com.example.studentmanager.config.exception.NotFoundException;
import com.example.studentmanager.constant.EmailConstant;
import com.example.studentmanager.constant.UrlConstant;
import com.example.studentmanager.dto.DataMailDTO;
import com.example.studentmanager.entity.User;
import com.example.studentmanager.events.RegistrationCompleteEvent;
import com.example.studentmanager.service.AuthService;
import com.example.studentmanager.utils.SendMail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
  private final AuthService authService;

  private final SendMail sendMail;
  private final ObjectMapper objectMapper;

  public RegistrationCompleteEventListener(AuthService authService, SendMail sendMail, ObjectMapper objectMapper) {
    this.authService = authService;
    this.sendMail = sendMail;
    this.objectMapper = objectMapper;
  }

  @SneakyThrows
  @Override
  public void onApplicationEvent(RegistrationCompleteEvent event) {
    JsonNode userJson = event.getUser();
    User user = objectMapper.readValue(userJson.asText(), User.class);
    String token = RandomStringUtils.randomNumeric(6);

    authService.saveVerificationTokenForUser(userJson, token);
    String url = event.getApplicationUrl() + "/api/v1" + UrlConstant.Auth.VERIFY_SIGNUP + "?token=" + token;
    String content = "Xin chào " + user.getName() +
        "\nChúng tôi đã nhận được yêu cầu đăng ký tài khoản của bạn.\n" +
        "Nhập mã xác nhận này để hoàn tất đăng ký: " + token +
        "\nHoặc bấm vào link sau: " + url;
    try {
      sendMail.sendMail(new DataMailDTO(user.getEmail(), EmailConstant.SUBJECT_REGISTER, content), null);
    } catch (MessagingException e) {
      String contentAdmin = "Không thể gửi token xác nhận đăng ký cho Tài khoản " + user.getEmail() +
          "\nHãy kiểm tra lại !!!";
      sendMail.sendMail(new DataMailDTO(EmailConstant.EMAIL_ADMIN, EmailConstant.NOTIFICATION_ERROR, contentAdmin), null);
      throw new NotFoundException("Send failed");
    }
  }
}