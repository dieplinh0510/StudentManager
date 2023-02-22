package com.example.studentmanager.events.listener;

import com.example.studentmanager.constant.EmailConstant;
import com.example.studentmanager.constant.UrlConstant;
import com.example.studentmanager.events.PasswordResetCompleteEvent;
import com.example.studentmanager.config.exception.NotFoundException;
import com.example.studentmanager.dto.DataMailDTO;
import com.example.studentmanager.entity.User;
import com.example.studentmanager.service.AuthService;
import com.example.studentmanager.utils.SendMail;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class PassResetCompleteEventListener implements ApplicationListener<PasswordResetCompleteEvent> {

  private final AuthService authService;

  private final SendMail sendMail;

  public PassResetCompleteEventListener(AuthService authService, SendMail sendMail) {
    this.authService = authService;
    this.sendMail = sendMail;
  }

  @SneakyThrows
  @Override
  public void onApplicationEvent(PasswordResetCompleteEvent event) {
    User user = event.getUser();
    String token = RandomStringUtils.randomNumeric(6);

    authService.savePasswordResetToken(token, user);
    String url = event.getApplicationUrl() + "/api/v1" + UrlConstant.Auth.VERIFY_FORGOT_PASS + "?email=" + user.getEmail() + "&token=" + token;
    String content = "Xin chào " + user.getName() +
        "\nChúng tôi đã nhận được yêu cầu đổi mật khẩu tài khoản của bạn.\n" +
        "Nhập mã xác nhận này để đổi mật khẩu: " + token +
        "\nHoặc bấm vào link sau: " + url;
    try {
      sendMail.sendMail(new DataMailDTO(user.getEmail(), EmailConstant.SUBJECT_FORGOT_PASS, content), null);
    } catch (MessagingException e) {
      String contentAdmin = "Không thể gửi token xác nhận quên mật khẩu cho Tài khoản " + user.getEmail() +
          "\nHãy kiểm tra lại !!!";
      sendMail.sendMail(new DataMailDTO(EmailConstant.EMAIL_ADMIN, EmailConstant.NOTIFICATION_ERROR, contentAdmin), null);
      throw new NotFoundException("Send failed");
    }
  }
}
