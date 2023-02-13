package com.example.studentmanager.service;

import com.example.studentmanager.dto.StudentSubjectDTO;
import com.example.studentmanager.entity.StudentSubject;
import com.example.studentmanager.payload.response.TrueFalseResponse;

import java.util.List;

public interface StudentSubjectService {
  List<StudentSubject> getAllStudentSubject();
  StudentSubject getStudentSubjectById(Long studentsubjectId);
  StudentSubject createStudentSubject(Long userId, Long subjectId, StudentSubjectDTO studentSubjectDTO);
  StudentSubject changeStudentSubject(Long studentsubjectId, StudentSubjectDTO studentSubjectDTO);
  TrueFalseResponse deleteStudentSubject(Long studentsubjectId);
  StudentSubject getScoreSubject(Long userId, Long subjectId);
}
