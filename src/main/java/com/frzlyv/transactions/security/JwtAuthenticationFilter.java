package com.frzlyv.transactions.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JwtAuthenticationFilter
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  public JwtAuthenticationFilter(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    // Extract auth header from req
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String username;

    // If no header skip
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    // Extract raw token by removing Bearer
    jwt = authHeader.substring(7);

    // Extract username claim from token
    username = jwtService.extractUsername(jwt);

    System.out.println("Filter intercepted valid header! Extracted username: " + username);

    // Pass the request to next filter
    filterChain.doFilter(request, response);
  }

}
