package com.frzlyv.transactions.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.frzlyv.transactions.auth.RegisterDto;
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

  public UserServiceImpl(UserRepository userRepository, Mapper<UserEntity, UserDto> userMapper,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
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
        .build();

    UserEntity savedUser = userRepository.save(user);
    return userMapper.toDto(savedUser);
  }

}
