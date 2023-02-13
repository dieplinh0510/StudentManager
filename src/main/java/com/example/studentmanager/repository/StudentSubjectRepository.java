package com.example.studentmanager.repository;

import com.example.studentmanager.entity.StudentSubject;
import com.example.studentmanager.entity.Subject;
import com.example.studentmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {
  StudentSubject findStudentSubjectById(Long studentSubjectId);
//  List<StudentSubject> findStudentSubjectsByUserAndActive(User user,Integer active);
  StudentSubject findStudentSubjectByUserAndSubject(User user, Subject subject);
  StudentSubject findStudentSubjectByUserAndStatus(User user, Integer status);

  StudentSubject findStudentSubjectByUserAndActive(User user, Integer active);
}
