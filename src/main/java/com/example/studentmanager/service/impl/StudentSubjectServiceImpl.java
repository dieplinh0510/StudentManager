package com.example.studentmanager.service.impl;

import com.example.studentmanager.config.exception.NotFoundException;
import com.example.studentmanager.dto.StudentSubjectDTO;
import com.example.studentmanager.entity.StudentSubject;
import com.example.studentmanager.entity.Subject;
import com.example.studentmanager.entity.User;
import com.example.studentmanager.payload.response.TrueFalseResponse;
import com.example.studentmanager.repository.CalendarRepository;
import com.example.studentmanager.repository.StudentSubjectRepository;
import com.example.studentmanager.repository.SubjectRepository;
import com.example.studentmanager.repository.UserRepository;
import com.example.studentmanager.service.StudentSubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {
  public final StudentSubjectRepository studentSubjectRepository;
  public final UserRepository userRepository;
  public final SubjectRepository subjectRepository;
  public final CalendarRepository calendarRepository;
  public final ModelMapper mapper;

  public StudentSubjectServiceImpl(StudentSubjectRepository studentSubjectRepository, UserRepository userRepository, SubjectRepository subjectRepository, CalendarRepository calendarRepository, ModelMapper mapper) {
    this.studentSubjectRepository = studentSubjectRepository;
    this.userRepository = userRepository;
    this.subjectRepository = subjectRepository;
    this.calendarRepository = calendarRepository;
    this.mapper = mapper;
  }

  @Override
  public List<StudentSubject> getAllStudentSubject() {
    List<StudentSubject> studentSubjects = studentSubjectRepository.findAll();
    return studentSubjects;
  }

  @Override
  public StudentSubject getStudentSubjectById(Long studentsubjectId) {
    StudentSubject studentSubject = studentSubjectRepository.findStudentSubjectById(studentsubjectId);
    checkNotFoundStudentSubject(studentSubject);
    return studentSubject;
  }

  @Override
  public StudentSubject createStudentSubject(Long userId, Long subjectId, StudentSubjectDTO studentSubjectDTO) {
    User user = userRepository.findUserById(userId);
    Subject subject = subjectRepository.findSubjectById(subjectId);
    if (user == null) {
      throw new NotFoundException("Not found user");
    }
    if (subject == null) {
      throw new NotFoundException("Not found subject");
    }

//    List<StudentSubject> studentSubjects = studentSubjectRepository.findStudentSubjectsByUserAndActive(user, 1);
//    for (StudentSubject studentSubject:studentSubjects) {
////      calendarRepository.findCalendarBySubjectAndTimeStart()
//    }
    return null;
  }


  @Override
  public StudentSubject changeStudentSubject(Long studentsubjectId, StudentSubjectDTO studentSubjectDTO) {
    StudentSubject studentSubject = studentSubjectRepository.findStudentSubjectById(studentsubjectId);
    checkNotFoundStudentSubject(studentSubject);
    mapper.map(studentSubjectDTO, studentSubject);
    return studentSubjectRepository.save(studentSubject);
  }


  @Override
  public TrueFalseResponse deleteStudentSubject(Long studentsubjectId) {
    StudentSubject studentSubject = studentSubjectRepository.findStudentSubjectById(studentsubjectId);
    checkNotFoundStudentSubject(studentSubject);
    studentSubjectRepository.delete(studentSubject);
    return new TrueFalseResponse(true);
  }

  @Override
  public StudentSubject getScoreSubject(Long userId, Long subjectId) {
    User user = userRepository.findUserById(userId);
    Subject subject = subjectRepository.findSubjectById(subjectId);
    StudentSubject studentSubject = studentSubjectRepository.findStudentSubjectByUserAndSubject(user, subject);
    return studentSubject;
  }

  public void checkNotFoundStudentSubject(StudentSubject studentSubject) {
    if (studentSubject == null) {
      throw new NotFoundException("Not found student subject");
    }
  }
}
