package com.example.studentmanager.service;

import com.example.studentmanager.dto.SemesterDTO;
import com.example.studentmanager.entity.Semester;
import com.example.studentmanager.entity.Subject;
import com.example.studentmanager.payload.response.TrueFalseResponse;

import java.util.List;


public interface SemesterService {
  List<Semester> getAllSemester();

  Semester getSemesterById(Long semesterId);

  Semester createSemester(SemesterDTO semesterDTO);

  Semester changeSemester(Long semesterId, SemesterDTO semesterDTO);

  TrueFalseResponse deleteSemester(Long semesterId);

  List<Subject> getSubjectInSemester(Long semesterId);
}
