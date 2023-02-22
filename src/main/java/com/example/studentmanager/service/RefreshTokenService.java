package com.example.studentmanager.service;

import com.example.studentmanager.entity.RefreshToken;
import com.example.studentmanager.payload.response.TrueFalseResponse;

public interface RefreshTokenService {
  RefreshToken createRefreshToken(Long userId);

  TrueFalseResponse deleteByUserId(Long userId);

}
