package com.example.studentmanager.service.impl;

import com.example.studentmanager.config.exception.NotFoundException;
import com.example.studentmanager.dto.RoleDTO;
import com.example.studentmanager.entity.Role;
import com.example.studentmanager.payload.response.TrueFalseResponse;
import com.example.studentmanager.repository.RoleRepository;
import com.example.studentmanager.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
  public final RoleRepository rolerepository;
  public final ModelMapper mapper;

  public RoleServiceImpl(RoleRepository rolerepository, ModelMapper mapper) {
    this.rolerepository = rolerepository;
    this.mapper = mapper;
  }

  @Override
  public List<?> getAllRole() {
    List<Role> roles = rolerepository.findAll();
    return roles;
  }

  @Override
  public Role getRoleById(Long roleId) {
    Role role = rolerepository.findRoleById(roleId);
    checkNotFoundRole(role);
    return role;
  }

  @Override
  public Role createRole(RoleDTO roleDTO) {
    Role role = mapper.map(roleDTO, Role.class);
    return rolerepository.save(role);
  }

  @Override
  public Role changeRoleById(Long roleId, RoleDTO roleDTO) {
    Role role = rolerepository.findRoleById(roleId);
    checkNotFoundRole(role);
    mapper.map(roleDTO, role);
    return rolerepository.save(role);
  }


  @Override
  public TrueFalseResponse deleteRoleById(Long roleId) {
    Role role = rolerepository.findRoleById(roleId);
    checkNotFoundRole(role);
    rolerepository.delete(role);
    return new TrueFalseResponse(true);
  }

  public void checkNotFoundRole(Role role) {
    if (role == null) {
      throw new NotFoundException("Not found role");
    }
  }
}
