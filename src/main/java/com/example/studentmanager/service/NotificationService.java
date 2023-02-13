package com.example.studentmanager.service;

import com.example.studentmanager.dto.NotificationDTO;
import com.example.studentmanager.entity.Notification;
import com.example.studentmanager.payload.response.TrueFalseResponse;

import java.util.List;

public interface NotificationService {
  List<Notification> getAllNotification();

  Notification getNotificationById(Long notificationId);
  Notification createNotification(NotificationDTO notificationDTO);
  Notification changeNotificationById(Long notificationId);
  TrueFalseResponse deleteNotification(Long notificationId);
}
