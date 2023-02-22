package com.example.studentmanager.service;

import com.example.studentmanager.dto.UserDTO;
import com.example.studentmanager.entity.User;
import com.example.studentmanager.payload.response.TrueFalseResponse;

import java.util.List;

public interface UserService {
  List<User> getAllUser();
  User getUserById(Long userId);
  User changeUserById(Long userId, UserDTO userDTO);

  TrueFalseResponse deleteUserById(Long userId);

  User save(User user);
  User getUserByEmail(String email);
}
