package com.example.studentmanager.repository;

import com.example.studentmanager.entity.Calendar;
import com.example.studentmanager.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
  Calendar findCalendarById(Long calendarId);
  Calendar findCalendarBySubjectAndTimeStart(Subject subject, Date date);
  List<Calendar> getCalendarsBySubjectId(Long subjectId);
}
