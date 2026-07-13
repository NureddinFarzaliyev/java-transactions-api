package com.frzlyv.transactions.user;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.frzlyv.transactions.auth.LoginDto;
import com.frzlyv.transactions.auth.LoginResponseDto;
import com.frzlyv.transactions.auth.RegisterDto;
import com.frzlyv.transactions.security.JwtService;
import com.frzlyv.transactions.shared.Mapper;
import com.frzlyv.transactions.shared.exceptions.UserAlreadyExistsException;

/**
 * UserServiceImpl
 */
@Component
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final Mapper<UserEntity, UserDto> userMapper;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  public UserServiceImpl(UserRepository userRepository, Mapper<UserEntity, UserDto> userMapper,
      PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
  }

  @Override
  public UserDto register(RegisterDto registerDto) {
    if (userRepository.existsByEmail(registerDto.getEmail())) {
      throw new UserAlreadyExistsException("User with this email already exists: "
          + registerDto.getEmail());
    }

    UserEntity user = UserEntity
        .builder()
        .email(registerDto.getEmail())
        .password(passwordEncoder.encode(registerDto.getPassword()))
        .role(registerDto.getRole() != null ? registerDto.getRole() : "USER")
        .build();

    UserEntity savedUser = userRepository.save(user);
    return userMapper.toDto(savedUser);
  }

  @Override
  public LoginResponseDto login(LoginDto loginDto) {

    // AuthenticationManager will use CustomUserDetailsService
    // to automatically check username/password on db
    // and will throw error if something goes wrong
    authenticationManager
        .authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()));

    String token = jwtService.generateToken(loginDto.getEmail());
    return LoginResponseDto.builder().token(token).build();
  }

}
