package com.example.studentmanager.service.impl;

import com.example.studentmanager.config.exception.DuplicateException;
import com.example.studentmanager.config.exception.InvalidException;
import com.example.studentmanager.config.exception.LoginException;
import com.example.studentmanager.constant.AuthenticationProvider;
import com.example.studentmanager.constant.RoleConstant;
import com.example.studentmanager.dto.UserDTO;
import com.example.studentmanager.entity.PasswordResetToken;
import com.example.studentmanager.entity.RefreshToken;
import com.example.studentmanager.entity.User;
import com.example.studentmanager.entity.VerificationToken;
import com.example.studentmanager.payload.auth.AuthenticationRequest;
import com.example.studentmanager.payload.response.AuthenticationResponse;
import com.example.studentmanager.payload.response.TrueFalseResponse;
import com.example.studentmanager.repository.PasswordResetTokenRepository;
import com.example.studentmanager.repository.RoleRepository;
import com.example.studentmanager.repository.UserRepository;
import com.example.studentmanager.repository.VerificationTokenRepository;
import com.example.studentmanager.service.AuthService;
import com.example.studentmanager.service.RefreshTokenService;
import com.example.studentmanager.service.UserService;
import com.example.studentmanager.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
  private final UserService userService;
  private final RefreshTokenService refreshTokenService;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final VerificationTokenRepository verificationTokenRepository;
  private final PasswordResetTokenRepository passwordResetTokenRepository;
  private final AuthenticationManager authenticationManager;
  private final MyUserDetailService myUserDetailService;
  private final JwtTokenUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper mapper;

  private final ObjectMapper objectMapper;

  public AuthServiceImpl(UserService userService, RefreshTokenService refreshTokenService, UserRepository userRepository, RoleRepository roleRepository, VerificationTokenRepository verificationTokenRepository, PasswordResetTokenRepository passwordResetTokenRepository, AuthenticationManager authenticationManager, MyUserDetailService myUserDetailService, JwtTokenUtil jwtUtil, PasswordEncoder passwordEncoder, ModelMapper mapper, ObjectMapper objectMapper) {
    this.userService = userService;
    this.refreshTokenService = refreshTokenService;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.verificationTokenRepository = verificationTokenRepository;
    this.passwordResetTokenRepository = passwordResetTokenRepository;
    this.authenticationManager = authenticationManager;
    this.myUserDetailService = myUserDetailService;
    this.jwtUtil = jwtUtil;
    this.passwordEncoder = passwordEncoder;
    this.mapper = mapper;
    this.objectMapper = objectMapper;
  }

  @Override
  public AuthenticationResponse signIn(AuthenticationRequest request) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmailOrPhone(), request.getPassword())
      );
    } catch (BadCredentialsException e) {
      throw new LoginException("Incorrect");
    }
    UserDetails userDetails = myUserDetailService.loadUserByUsername(request.getEmailOrPhone());
    String jwt = jwtUtil.generateToken(userDetails);
    User user = userService.getUserByEmail(userDetails.getUsername());
    RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

    user.setStatusBase(true);
    userService.save(user);
    return new AuthenticationResponse(jwt, refreshToken.getToken(), user);
  }

  @Override
  @SneakyThrows
  public JsonNode signup(UserDTO userDTO) {
    if (userRepository.findByEmail(userDTO.getEmail()) != null) {
      throw new DuplicateException("Email has already exists");
    }
    userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

    User user = mapper.map(userDTO, User.class);

    String userString = objectMapper.writeValueAsString(user);

    return objectMapper.readTree(userString);
  }

  @Override
  public void saveVerificationTokenForUser(JsonNode user, String token) {
    VerificationToken verificationToken = new VerificationToken(user, token);
    verificationTokenRepository.save(verificationToken);
  }

  @Override
  public TrueFalseResponse validateVerificationToken(String token) {
    VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
    Calendar calendar = Calendar.getInstance();
    if (verificationToken == null) {
      throw new InvalidException("Invalid token");
    } else if ((verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
      verificationTokenRepository.delete(verificationToken);
      throw new InvalidException("Expired token");
    }

    User user = mapper.map(verificationToken.getUser(), User.class);
    user.setAuthProvider(AuthenticationProvider.LOCAL);
    user.setRole(roleRepository.findByName(RoleConstant.USER));
    userService.save(user);
    verificationTokenRepository.delete(verificationToken);

    return new TrueFalseResponse(true);
  }


  @Override
  public void savePasswordResetToken(String token, User user) {
    PasswordResetToken passwordresetToken = new PasswordResetToken(user, token);
    passwordResetTokenRepository.save(passwordresetToken);
  }


  @Override
  public TrueFalseResponse forgotPassword(String email, String token, String newPassword) {
    try {
      User user = userService.getUserByEmail(email);
      user.setPassword(passwordEncoder.encode(newPassword));
      passwordResetTokenRepository.delete(passwordResetTokenRepository.findByToken(token));
      userService.save(user);
      return new TrueFalseResponse(true);
    } catch (Exception ex) {
      return new TrueFalseResponse(false);
    }
  }

  @Override
  public TrueFalseResponse validateToken(AuthenticationResponse authenticationResponse) {
    try {
      String jwt = authenticationResponse.getToken();
      String username = jwtUtil.extractUsername(jwt);
      UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
      return new TrueFalseResponse(jwtUtil.validateToken(jwt, userDetails));
    } catch (Exception ex) {
      return new TrueFalseResponse(false);
    }
  }

  @Override
  public TrueFalseResponse logout(Long id) {
    try {
      User user = userService.getUserById(id);
      user.setStatusBase(false);
      refreshTokenService.deleteByUserId(user.getId());
      return new TrueFalseResponse(true);
    } catch (Exception ex) {
      return new TrueFalseResponse(false);
    }
  }

}
