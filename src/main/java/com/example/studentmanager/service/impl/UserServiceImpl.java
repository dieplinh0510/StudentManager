package com.example.studentmanager.service.impl;

import com.example.studentmanager.config.exception.NotFoundException;
import com.example.studentmanager.dto.UserDTO;
import com.example.studentmanager.entity.User;
import com.example.studentmanager.payload.response.TrueFalseResponse;
import com.example.studentmanager.repository.UserRepository;
import com.example.studentmanager.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  public final UserRepository userRepository;
  public final ModelMapper mapper;

  public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
    this.userRepository = userRepository;
    this.mapper = mapper;
  }

  @Override
  public List<User> getAllUser() {
    List<User> users = userRepository.findAll();
    return users;
  }

  @Override
  public User getUserById(Long userId) {
    User user = userRepository.findUserById(userId);
    checkNotFoundUser(user);
    return user;
  }

  @Override
  public User changeUserById(Long userId, UserDTO userDTO) {
    User user = userRepository.findUserById(userId);
    checkNotFoundUser(user);
    mapper.map(userDTO, user);
    return userRepository.save(user);
  }

  @Override
  public TrueFalseResponse deleteUserById(Long userId) {
    User user = userRepository.findUserById(userId);
    checkNotFoundUser(user);
    userRepository.delete(user);
    return new TrueFalseResponse(true);
  }

  public void checkNotFoundUser(User user) {
    if (user == null) {
      throw new NotFoundException("Not found user");
    }
  }
}
