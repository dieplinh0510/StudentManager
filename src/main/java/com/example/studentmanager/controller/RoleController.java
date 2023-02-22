package com.example.studentmanager.controller;

import com.example.studentmanager.constant.UrlConstant;
import com.example.studentmanager.base.RestApiV1;
import com.example.studentmanager.base.VsResponseUtil;
import com.example.studentmanager.dto.RoleDTO;
import com.example.studentmanager.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class RoleController {
  public final RoleService roleService;


  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping(UrlConstant.Role.GET_ALL_ROLE)
  public ResponseEntity<?> getAllRole (){
    return VsResponseUtil.ok(roleService.getAllRole());
  }

  @GetMapping(UrlConstant.Role.GET_ROLE)
  public ResponseEntity<?> getRoleById(@PathVariable Long roleId){
    return VsResponseUtil.ok(roleService.getRoleById(roleId));
  }

  @PostMapping(UrlConstant.Role.CREATE_ROLE)
  public ResponseEntity<?> createRole(@RequestBody RoleDTO roleDTO){
    return VsResponseUtil.ok(roleService.createRole(roleDTO));
  }

  @PatchMapping(UrlConstant.Role.CHANGE_USER)
  public ResponseEntity<?> changeRoleById(@PathVariable Long roleId, @RequestBody RoleDTO roleDTO){
    return VsResponseUtil.ok(roleService.changeRoleById(roleId, roleDTO));
  }

  @DeleteMapping(UrlConstant.Role.DELETE_USER)
  public ResponseEntity<?> deleteRoleById(@PathVariable Long roleId){
    return VsResponseUtil.ok(roleService.deleteRoleById(roleId));
  }
}
