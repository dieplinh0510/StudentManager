package com.example.studentmanager.controller;

import com.example.studentmanager.Constant.UrlConstant;
import com.example.studentmanager.base.RestApiV1;
import com.example.studentmanager.base.VsResponseUtil;
import com.example.studentmanager.dto.CalendarDTO;
import com.example.studentmanager.entity.Calendar;
import com.example.studentmanager.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class CalendarController {
  public final CalendarService calendarService;

  public CalendarController(CalendarService calendarService) {
    this.calendarService = calendarService;
  }


  @GetMapping(UrlConstant.Calendar.GET_ALL_CALENDAR)
  public ResponseEntity<?> getAllCalendar(){
    return VsResponseUtil.ok(calendarService.getAllCalendar());
  }

  @GetMapping(UrlConstant.Calendar.GET_CALENDAR)
  public ResponseEntity<?> getCalendarById(@PathVariable Long calendarId){
    return VsResponseUtil.ok(calendarService.getCalendarById(calendarId));
  }

  @GetMapping(UrlConstant.Calendar.GET_CALENDARS_BY_SUBJECT)
  public ResponseEntity<?> getCalendarsBySubject(@PathVariable Long subjectId){
    return VsResponseUtil.ok(calendarService.getCalendarsBySubject(subjectId));
  }

  @PostMapping(UrlConstant.Calendar.CREATE_CALENDAR)
  public ResponseEntity<?> createCalendar(@RequestBody CalendarDTO calendarDTO){
    return VsResponseUtil.ok(calendarService.createCalendar(calendarDTO));
  }

  @PatchMapping(UrlConstant.Calendar.CHANGE_CALENDAR)
  public ResponseEntity<?> changeCalendarById(@PathVariable Long calendarId, @RequestBody CalendarDTO calendarDTO){
    return VsResponseUtil.ok(calendarService.changeCalendarById(calendarId, calendarDTO));
  }

  @DeleteMapping(UrlConstant.Calendar.DELETE_CALENDAR)
  public ResponseEntity<?> deleteCalendarById(@PathVariable Long calendarId){
    return VsResponseUtil.ok(calendarService.deleteCalendarById(calendarId));
  }

}
