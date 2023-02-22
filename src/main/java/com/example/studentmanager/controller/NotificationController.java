package com.example.studentmanager.controller;

import com.example.studentmanager.constant.UrlConstant;
import com.example.studentmanager.base.RestApiV1;
import com.example.studentmanager.base.VsResponseUtil;
import com.example.studentmanager.dto.NotificationDTO;
import com.example.studentmanager.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class NotificationController {
  public final NotificationService notificationService;


  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @GetMapping(UrlConstant.Notification.GET_ALL_NOTIFICATION)
  public ResponseEntity<?> getAllNotification(){
    return VsResponseUtil.ok(notificationService.getAllNotification());
  }

  @GetMapping(UrlConstant.Notification.GET_NOTIFICATION)
  public ResponseEntity<?> getNotificationById(@PathVariable Long notificationId){
    return VsResponseUtil.ok(notificationService.getNotificationById(notificationId));
  }

  @PostMapping(UrlConstant.Notification.CREATE_NOTIFICATION)
  public ResponseEntity<?> createNotification(@RequestBody NotificationDTO notificationDTO){
    return VsResponseUtil.ok(notificationService.createNotification(notificationDTO));
  }

  @PatchMapping(UrlConstant.Notification.CHANGE_NOTIFICATION)
  public ResponseEntity<?> changeNotificationById(@PathVariable Long notificationId){
    return VsResponseUtil.ok(notificationService.changeNotificationById(notificationId));
  }

  @DeleteMapping(UrlConstant.Notification.DELETE_NOTIFICATION)
  public ResponseEntity<?> deleteNotificationById(@PathVariable Long notificationId){
    return  VsResponseUtil.ok(notificationService.deleteNotification(notificationId));
  }
}
