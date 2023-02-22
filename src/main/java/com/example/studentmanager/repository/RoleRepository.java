package com.example.studentmanager.repository;

import com.example.studentmanager.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findRoleById(Long role);

  Role findByName(String name);
}
