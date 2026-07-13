package com.frzlyv.transactions.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
  private final UserDetailsService userDetailsService;

  public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
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

    // Authenticate if we have username && not already authenticated
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      // Fetch user data from db
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
          userDetails, // User identity
          null, // Credentials (We don't need passwords for stateless JWT validation)
          userDetails.getAuthorities()); // Authorities/Roles

      // Build details from request metadata
      authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      // Update the security context with new data
      SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    // Pass the request to next filter
    filterChain.doFilter(request, response);
  }

}
