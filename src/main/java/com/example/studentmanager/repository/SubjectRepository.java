package com.example.studentmanager.repository;

import com.example.studentmanager.entity.Semester;
import com.example.studentmanager.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
  Subject findSubjectById(Long id);
  List<Subject> getSubjectsBySemester(Semester semester);
}
