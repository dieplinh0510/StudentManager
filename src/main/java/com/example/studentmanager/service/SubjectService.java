package com.example.studentmanager.service;

import com.example.studentmanager.dto.SubjectDTO;
import com.example.studentmanager.entity.Subject;
import com.example.studentmanager.payload.response.TrueFalseResponse;

import java.util.List;


public interface SubjectService {
  List<Subject> getAllSuject();

  Subject getSubjectById(Long subjectId);

  Subject createSubject(SubjectDTO subjectDTO);

  Subject changeSubjectById(Long subjectId, SubjectDTO subjectDTO);

  TrueFalseResponse deleteSubjectById(Long subjectId);
}
