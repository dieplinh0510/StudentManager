package com.example.studentmanager.service.impl;

import com.example.studentmanager.entity.RefreshToken;
import com.example.studentmanager.payload.response.TrueFalseResponse;
import com.example.studentmanager.repository.RefreshTokenRepository;
import com.example.studentmanager.service.RefreshTokenService;
import com.example.studentmanager.service.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

  private final RefreshTokenRepository refreshTokenRepository;
  private final UserService userService;

  public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, UserService userService) {
    this.refreshTokenRepository = refreshTokenRepository;
    this.userService = userService;
  }

  @Override
  public RefreshToken createRefreshToken(Long userId) {
    RefreshToken refreshToken = new RefreshToken(UUID.randomUUID().toString(), userService.getUserById(userId));
    return refreshTokenRepository.save(refreshToken);
  }

  @Override
  public TrueFalseResponse deleteByUserId(Long userId) {
    refreshTokenRepository.deleteByUser(userService.getUserById(userId));
    return new TrueFalseResponse(true);
  }
}
