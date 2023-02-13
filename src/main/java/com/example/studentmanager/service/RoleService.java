package com.example.studentmanager.service;

import com.example.studentmanager.dto.RoleDTO;
import com.example.studentmanager.entity.Role;
import com.example.studentmanager.payload.response.TrueFalseResponse;

import java.util.List;

public interface RoleService {
  List<?> getAllRole();
  Role getRoleById(Long roleId);

  Role createRole(RoleDTO roleDTO);
  Role changeRoleById(Long roleId, RoleDTO roleDTO);

  TrueFalseResponse deleteRoleById(Long roleId);
}
