package com.example.studentmanager.service.impl;

import com.example.studentmanager.constant.TypeNotification;
import com.example.studentmanager.config.exception.NotFoundException;
import com.example.studentmanager.dto.NotificationDTO;
import com.example.studentmanager.entity.Notification;
import com.example.studentmanager.entity.StudentSubject;
import com.example.studentmanager.entity.User;
import com.example.studentmanager.payload.response.TrueFalseResponse;
import com.example.studentmanager.repository.NotificationRepository;
import com.example.studentmanager.repository.StudentSemesterRepository;
import com.example.studentmanager.repository.StudentSubjectRepository;
import com.example.studentmanager.repository.UserRepository;
import com.example.studentmanager.service.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NotificationServiceImpl implements NotificationService {
  public final NotificationRepository notificationRepository;
  public final UserRepository userRepository;
  public final StudentSemesterRepository studentSemesterRepository;
  public final StudentSubjectRepository studentSubjectRepository;
  public final ModelMapper mapper;

  public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository, StudentSemesterRepository studentSemesterRepository, StudentSubjectRepository subjectRepository, StudentSubjectRepository studentSubjectRepository, ModelMapper mapper) {
    this.notificationRepository = notificationRepository;
    this.userRepository = userRepository;
    this.studentSemesterRepository = studentSemesterRepository;
    this.studentSubjectRepository = studentSubjectRepository;
    this.mapper = mapper;
  }


  @Override
  public List<Notification> getAllNotification() {
    List<Notification> notifications = notificationRepository.findAll();
    return notifications;
  }

  @Override
  public Notification getNotificationById(Long notificationId) {
    Notification notification = notificationRepository.findNotificationById(notificationId);
    checkNotFoundNotification(notification);
    return notification;
  }

  @Override
  public Notification createNotification(NotificationDTO notificationDTO) {
    String content = "";
    String subject = "";
    User user = userRepository.findUserById(notificationDTO.getUserId());
    if (user == null){
      throw new NotFoundException("Khong ton tai user");
    }
    if (Objects.equals(notificationDTO.getType(), TypeNotification.SCHOLARSHIP.getValue())){
      if (studentSemesterRepository.getStudentSemesterByUserAndIsScholarship(user, 1) != null){
        content = user.getName() + " da nhan duoc hoc bong";
        subject = "Hoc bong";
      }
    }
    if (Objects.equals(notificationDTO.getType(), TypeNotification.LEARNMORE.getValue())){
      StudentSubject studentSubject = studentSubjectRepository.findStudentSubjectByUserAndStatus(user, 0);
      if (studentSubject !=null){
        content = user.getName() + " truot mon " + studentSubject.getSubject().getName();
        subject = "Truot mon";
      }
    }
    if (Objects.equals(notificationDTO.getType(), TypeNotification.DEBT.getValue())){
      StudentSubject studentSubject = studentSubjectRepository.findStudentSubjectByUserAndActive(user, 1);
      if (studentSubject != null ){
        content = user.getName()+ " no them "+ studentSubject.getSubject().getTuition() +" vao tai khoan";
        subject = "Cong no";
      }
    }
    if (Objects.equals(notificationDTO.getType(), TypeNotification.SUCCESSFULREGISTRATION.getValue())){
      StudentSubject studentSubject = studentSubjectRepository.findStudentSubjectByUserAndActive(user, 1);
      if (studentSubject != null ){
        content = user.getName()+ " dang ki thanh cong mon "+ studentSubject.getSubject().getName();
        subject = "Dang ky thanh cong mon hoc";
      }
    }
    Notification notification = mapper.map(notificationDTO, Notification.class);
    notification.setContent(content);
    notification.setSubject(subject);
    return notificationRepository.save(notification);
  }

  @Override
  public Notification changeNotificationById(Long notificationId) {
    Notification notification = notificationRepository.findNotificationById(notificationId);
    checkNotFoundNotification(notification);
    notification.setIsRead(true);
    return notificationRepository.save(notification);
  }

  @Override
  public TrueFalseResponse deleteNotification(Long notificationId) {
    Notification notification = notificationRepository.findNotificationById(notificationId);
    checkNotFoundNotification(notification);
    notificationRepository.delete(notification);
    return new TrueFalseResponse(true);
  }

  public void checkNotFoundNotification(Notification notification) {
    if (notification == null) {
      throw new NotFoundException("Not found notification");
    }
  }
}
