package com.example.studentmanager.filter;

import com.example.studentmanager.service.impl.MyUserDetailService;
import com.example.studentmanager.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
  @Autowired
  private MyUserDetailService myUserDetailService;

  @Autowired
  private JwtTokenUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      final String authorizationHeader = request.getHeader("Authorization");
      String email = null;
      String jwt = null;
      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
        jwt = authorizationHeader.substring(7);
        email = jwtUtil.extractUsername(jwt);
      }
      if (email != null) {
        UserDetails userDetails = myUserDetailService.loadUserByUsername(email);
        if (jwtUtil.validateToken(jwt, userDetails)) {
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities()
          );
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
      }

    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    filterChain.doFilter(request, response);
  }
}
