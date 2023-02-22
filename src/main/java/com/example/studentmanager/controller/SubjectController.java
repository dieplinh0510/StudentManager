package com.example.studentmanager.controller;

import com.example.studentmanager.constant.UrlConstant;
import com.example.studentmanager.base.RestApiV1;
import com.example.studentmanager.base.VsResponseUtil;
import com.example.studentmanager.dto.SubjectDTO;
import com.example.studentmanager.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class SubjectController {
  public  final SubjectService subjectService ;

  public SubjectController(SubjectService subjectService) {
    this.subjectService = subjectService;
  }

  @GetMapping(UrlConstant.Subject.GET_ALL_SUBJECT)
  public ResponseEntity<?> getAllSubject(){
    return VsResponseUtil.ok(subjectService.getAllSuject());
  }

  @GetMapping(UrlConstant.Subject.GET_SUBJECT)
  public ResponseEntity<?> getSubjectById(@PathVariable Long subjectId){
    return VsResponseUtil.ok(subjectService.getSubjectById(subjectId));
  }

  @PostMapping(UrlConstant.Subject.CREATE_SUBJECT)
  public ResponseEntity<?> createSubject(@RequestBody SubjectDTO subjectDTO){
    return VsResponseUtil.ok(subjectService.createSubject(subjectDTO));
  }

  @PatchMapping(UrlConstant.Subject.CHANGE_SUBJECT)
  public ResponseEntity<?> changeSubjectById(@PathVariable Long subjectId, @RequestBody SubjectDTO subjectDTO){
    return VsResponseUtil.ok(subjectService.changeSubjectById(subjectId, subjectDTO));
  }

  @DeleteMapping(UrlConstant.Subject.DELETE_SUBJECT)
  public ResponseEntity<?> deleteSubjectById(@PathVariable Long subjectId){
    return VsResponseUtil.ok(subjectService.deleteSubjectById(subjectId));
  }


}
