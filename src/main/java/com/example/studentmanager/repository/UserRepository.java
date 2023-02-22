package com.example.studentmanager.repository;

import com.example.studentmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findUserById(Long userId);

  User findByEmailOrPhone(String email, String phone);

  User findByEmail(String email);
}
