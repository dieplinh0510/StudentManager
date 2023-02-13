package com.example.studentmanager.service;

import com.example.studentmanager.dto.CalendarDTO;
import com.example.studentmanager.entity.Calendar;
import com.example.studentmanager.payload.response.TrueFalseResponse;

import java.util.List;


public interface CalendarService {

  List<Calendar> getAllCalendar();

  Calendar getCalendarById(Long calendarId);

  Calendar createCalendar(CalendarDTO calendarDTO);

  Calendar changeCalendarById(Long calendarId, CalendarDTO calendarDTO);

  TrueFalseResponse deleteCalendarById(Long calendarId);

  List<Calendar> getCalendarsBySubject(Long subjectId);
}
