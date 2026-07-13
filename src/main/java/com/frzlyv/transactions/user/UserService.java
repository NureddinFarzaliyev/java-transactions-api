package com.frzlyv.transactions.user;

import com.frzlyv.transactions.auth.RegisterDto;

/**
 * UserService
 */
public interface UserService {

  UserDto register(RegisterDto registerDto);

}
