package com.example.studentmanager.controller;

import com.example.studentmanager.Constant.UrlConstant;
import com.example.studentmanager.base.RestApiV1;
import com.example.studentmanager.base.VsResponseUtil;
import com.example.studentmanager.dto.StudentSubjectDTO;
import com.example.studentmanager.service.StudentSubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class StudentSubjectController {

  public final StudentSubjectService studentSubjectService;

  public StudentSubjectController(StudentSubjectService studentSubjectService) {
    this.studentSubjectService = studentSubjectService;
  }

  @GetMapping(UrlConstant.StudentSubject.GET_ALL_STUDENTSUBJECT)
  public ResponseEntity<?> getAllStudentSubject(){
    return VsResponseUtil.ok(studentSubjectService.getAllStudentSubject());
  }

  @GetMapping(UrlConstant.StudentSubject.GET_STUDENTSUBJECT)
  public ResponseEntity<?> getStudentSubjectById(@PathVariable Long studentSubjectId){
    return VsResponseUtil.ok(studentSubjectService.getStudentSubjectById(studentSubjectId));
  }

  @PostMapping(UrlConstant.StudentSubject.CREATE_STUDENTSUBJECT)
  public ResponseEntity<?> createStudentSubject(@PathVariable Long userId, @PathVariable Long subjectId, @RequestBody StudentSubjectDTO studentSubjectDTO){
    return VsResponseUtil.ok(studentSubjectService.createStudentSubject(userId, subjectId, studentSubjectDTO));
  }

  @PatchMapping(UrlConstant.StudentSubject.CHANGE_STUDENTSUBJECT)
  public ResponseEntity<?> changeStudentSubjectById(@PathVariable Long studentsubjectId, @RequestBody StudentSubjectDTO studentSubjectDTO){
    return VsResponseUtil.ok(studentSubjectService.changeStudentSubject(studentsubjectId, studentSubjectDTO));
  }

  @DeleteMapping(UrlConstant.StudentSubject.DELETE_STUDENTSUBJECT)
  public ResponseEntity<?> deleteStudentSubject(@PathVariable Long studentsubjectId){
    return VsResponseUtil.ok(studentSubjectService.deleteStudentSubject(studentsubjectId));
  }

  @GetMapping(UrlConstant.StudentSubject.GET_SCORE)
  public ResponseEntity<?> getScoreSubject(@PathVariable Long userId, @PathVariable Long subjectId){
    return VsResponseUtil.ok(studentSubjectService.getScoreSubject(userId, subjectId));
  }

}

