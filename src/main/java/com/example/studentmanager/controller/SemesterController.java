package com.example.studentmanager.controller;

import com.example.studentmanager.Constant.UrlConstant;
import com.example.studentmanager.base.RestApiV1;
import com.example.studentmanager.base.VsResponseUtil;
import com.example.studentmanager.dto.SemesterDTO;
import com.example.studentmanager.service.SemesterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class SemesterController {
  public final SemesterService semesterService;

  public SemesterController(SemesterService semesterService) {
    this.semesterService = semesterService;
  }

  @GetMapping(UrlConstant.Semester.GET_ALL_SEMESTER)
  public ResponseEntity<?> getAllSemester(){
    return VsResponseUtil.ok(semesterService.getAllSemester());
  }

  @GetMapping(UrlConstant.Semester.GET_SEMESTER)
  public ResponseEntity<?> getSemesterById(@PathVariable Long semesterId){
    return VsResponseUtil.ok(semesterService.getSemesterById(semesterId));
  }

  @PostMapping(UrlConstant.Semester.CREATE_SEMESTER)
  public ResponseEntity<?> createSemester(@RequestBody SemesterDTO semesterDTO){
    return VsResponseUtil.ok(semesterService.createSemester(semesterDTO));
  }

  @PatchMapping(UrlConstant.Semester.CHANGE_SEMESTER)
  public ResponseEntity<?> changeSemester(@PathVariable Long semesterId, @RequestBody SemesterDTO semesterDTO){
    return VsResponseUtil.ok(semesterService.changeSemester(semesterId, semesterDTO));
  }

  @DeleteMapping(UrlConstant.Semester.DELETE_SEMESTER)
  public ResponseEntity<?> deleteSemester(@PathVariable Long semesterId){
    return VsResponseUtil.ok(semesterService.deleteSemester(semesterId));
  }

  @GetMapping(UrlConstant.Semester.GET_SUBJECTS_INSEMESTER)
  public ResponseEntity<?> getSubjectInSemester(@PathVariable Long semesterId){
    return VsResponseUtil.ok(semesterService.getSubjectInSemester(semesterId));
  }

}
