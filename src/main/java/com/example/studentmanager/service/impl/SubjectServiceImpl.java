package com.example.studentmanager.service.impl;

import com.example.studentmanager.config.exception.NotFoundException;
import com.example.studentmanager.dto.SubjectDTO;
import com.example.studentmanager.entity.Subject;
import com.example.studentmanager.payload.response.TrueFalseResponse;
import com.example.studentmanager.repository.SubjectRepository;
import com.example.studentmanager.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
  private SubjectRepository subjectRepository;
  private ModelMapper mapper;

  public SubjectServiceImpl(SubjectRepository subjectRepository) {
    this.subjectRepository = subjectRepository;
  }

  @Override
  public List<Subject> getAllSuject() {
    List<Subject> subjects = subjectRepository.findAll();

    return subjects;
  }

  @Override
  public Subject getSubjectById(Long subjectId) {
    Subject subject = subjectRepository.findSubjectById(subjectId);
    checkNotFoundSubject(subject);
    return subject;
  }

  @Override
  public Subject createSubject(SubjectDTO subjectDTO) {
    Subject subject = mapper.map(subjectDTO, Subject.class);
    return subjectRepository.save(subject);
  }

  @Override
  public Subject changeSubjectById(Long subjectId, SubjectDTO subjectDTO) {
    Subject subject = subjectRepository.findSubjectById(subjectId);
    checkNotFoundSubject(subject);
    mapper.map(subjectDTO, subject);
    return subjectRepository.save(subject);
  }

  @Override
  public TrueFalseResponse deleteSubjectById(Long subjectId) {
    Subject subject = subjectRepository.findSubjectById(subjectId);
    checkNotFoundSubject(subject);
    subjectRepository.delete(subject);
    return new TrueFalseResponse(true);
  }

  public void checkNotFoundSubject(Subject subject) {
    if (subject == null) {
      throw new NotFoundException("Not found subject");
    }
  }

}
