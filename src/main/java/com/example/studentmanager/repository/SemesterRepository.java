package com.example.studentmanager.repository;

import com.example.studentmanager.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
  Semester findSemesterById(Long semesterId);


}
