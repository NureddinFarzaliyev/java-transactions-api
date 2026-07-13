package com.frzlyv.transactions.user;

import com.frzlyv.transactions.auth.LoginDto;
import com.frzlyv.transactions.auth.LoginResponseDto;
import com.frzlyv.transactions.auth.RegisterDto;

/**
 * UserService
 */
public interface UserService {

  UserDto register(RegisterDto registerDto);

  LoginResponseDto login(LoginDto loginDto);

}
