package com.example.studentmanager.utils;

import com.example.studentmanager.dto.DataMailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Component
public class SendMail {
  @Autowired
  private JavaMailSender mailSender;

  public void sendMail(DataMailDTO dataMailDTO, MultipartFile[] files) throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
    helper.setTo(dataMailDTO.getTo());
    helper.setSubject(dataMailDTO.getSubject());
    helper.setText(dataMailDTO.getContent());
    if (files != null && files.length > 0) {
      for (MultipartFile file : files) {
        helper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);
      }
    }
    mailSender.send(mimeMessage);
  }
}
