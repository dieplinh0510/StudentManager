package com.example.studentmanager.service.impl;

import com.example.studentmanager.config.exception.NotFoundException;
import com.example.studentmanager.dto.CalendarDTO;
import com.example.studentmanager.entity.Calendar;
import com.example.studentmanager.payload.response.TrueFalseResponse;
import com.example.studentmanager.repository.CalendarRepository;
import com.example.studentmanager.service.CalendarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {
  private final CalendarRepository calendarRepository;
  private final ModelMapper mapper;

  public CalendarServiceImpl(CalendarRepository calendarRepository, ModelMapper mapper) {
    this.calendarRepository = calendarRepository;
    this.mapper = mapper;
  }

  @Override
  public List<Calendar> getAllCalendar() {
    return calendarRepository.findAll();
  }

  @Override
  public Calendar getCalendarById(Long calendarId) {
    Calendar calendar = calendarRepository.findCalendarById(calendarId);
    checkNotFoundCalendar(calendar);
    return calendar;
  }

  @Override
  public Calendar createCalendar(CalendarDTO calendarDTO) {
    Calendar calendar = mapper.map(calendarDTO, Calendar.class);
    return calendarRepository.save(calendar);
  }

  @Override
  public Calendar changeCalendarById(Long calendarId, CalendarDTO calendarDTO) {
    Calendar calendar = calendarRepository.findCalendarById(calendarId);
    checkNotFoundCalendar(calendar);
    mapper.map(calendarDTO, calendar);
    return calendarRepository.save(calendar);
  }

  @Override
  public TrueFalseResponse deleteCalendarById(Long calendarId) {
    Calendar calendar = calendarRepository.findCalendarById(calendarId);
    checkNotFoundCalendar(calendar);
    calendarRepository.delete(calendar);
    return new TrueFalseResponse(true);
  }

  @Override
  public List<Calendar> getCalendarsBySubject(Long subjectId) {
    List<Calendar> calendars = calendarRepository.getCalendarsBySubjectId(subjectId);
    return calendars;
  }

  public void checkNotFoundCalendar(Calendar calendar) {
    if (calendar == null) {
      throw new NotFoundException("Not found calendar");
    }
  }
}
