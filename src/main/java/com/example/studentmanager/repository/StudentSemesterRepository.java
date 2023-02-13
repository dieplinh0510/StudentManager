package com.example.studentmanager.repository;

import com.example.studentmanager.entity.StudentSemester;
import com.example.studentmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSemesterRepository extends JpaRepository<StudentSemester, Long> {
  StudentSemester getStudentSemesterByUserAndIsScholarship(User user, Integer isScholarship);
}
