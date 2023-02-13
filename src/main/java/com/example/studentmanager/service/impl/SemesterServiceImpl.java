package com.example.studentmanager.service.impl;

import com.example.studentmanager.config.exception.NotFoundException;
import com.example.studentmanager.dto.SemesterDTO;
import com.example.studentmanager.entity.Semester;
import com.example.studentmanager.entity.Subject;
import com.example.studentmanager.payload.response.TrueFalseResponse;
import com.example.studentmanager.repository.SemesterRepository;
import com.example.studentmanager.repository.SubjectRepository;
import com.example.studentmanager.service.SemesterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterServiceImpl implements SemesterService {
  public final SemesterRepository semesterRepository;
  public final ModelMapper mapper;
  public final SubjectRepository subjectRepository;

  public SemesterServiceImpl(SemesterRepository semesterRepository, ModelMapper mapper, SubjectRepository subjectRepository) {
    this.semesterRepository = semesterRepository;
    this.mapper = mapper;
    this.subjectRepository = subjectRepository;
  }

  @Override
  public List<Semester> getAllSemester() {
    List<Semester> semesters = semesterRepository.findAll();
    return semesters;
  }

  @Override
  public Semester getSemesterById(Long semesterId) {
    Semester semester = semesterRepository.findSemesterById(semesterId);
    checkNotFoundSemester(semester);
    return semester;
  }

  @Override
  public Semester createSemester(SemesterDTO semesterDTO) {
    Semester semester = mapper.map(semesterDTO, Semester.class);
    return semesterRepository.save(semester);
  }

  @Override
  public Semester changeSemester(Long semesterId, SemesterDTO semesterDTO) {
    Semester semester = semesterRepository.findSemesterById(semesterId);
    checkNotFoundSemester(semester);
    mapper.map(semesterDTO, semester);
    return semesterRepository.save(semester);
  }

  @Override
  public TrueFalseResponse deleteSemester(Long semesterId) {
    Semester semester = semesterRepository.findSemesterById(semesterId);
    checkNotFoundSemester(semester);
    semesterRepository.delete(semester);
    return new TrueFalseResponse(true);
  }

  @Override
  public List<Subject> getSubjectInSemester(Long semesterId) {
    Semester semester = semesterRepository.findSemesterById(semesterId);
    checkNotFoundSemester(semester);
    List<Subject> subjects = subjectRepository.getSubjectsBySemester(semester);
    return subjects;
  }

  public void checkNotFoundSemester(Semester semester) {
    if (semester == null) {
      throw new NotFoundException("Not found semester");
    }
  }
}
