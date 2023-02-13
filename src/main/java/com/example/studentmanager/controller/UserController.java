package com.example.studentmanager.controller;

import com.example.studentmanager.Constant.UrlConstant;
import com.example.studentmanager.base.RestApiV1;
import com.example.studentmanager.base.VsResponseUtil;
import com.example.studentmanager.dto.UserDTO;
import com.example.studentmanager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class UserController {
  public final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(UrlConstant.User.GET_ALL_USER)
  public ResponseEntity<?> getAllUser(){
    return VsResponseUtil.ok(userService.getAllUser());
  }

  @GetMapping(UrlConstant.User.GET_USER)
  public ResponseEntity<?> getUserById(@PathVariable Long userId){
    return VsResponseUtil.ok(userService.getUserById(userId));
  }

  @PostMapping(UrlConstant.User.CHANGE_USER)
  public ResponseEntity<?> changeUserById(@PathVariable Long userId, @RequestBody UserDTO userDTO){
    return VsResponseUtil.ok(userService.changeUserById(userId, userDTO));
  }

  @DeleteMapping(UrlConstant.User.DELETE_USER)
  public ResponseEntity<?> deleteUserById(@PathVariable Long userId){
    return VsResponseUtil.ok(userService.deleteUserById(userId));
  }
}
