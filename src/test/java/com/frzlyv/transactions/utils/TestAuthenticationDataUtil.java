package com.frzlyv.transactions.utils;

import com.frzlyv.transactions.auth.LoginDto;
import com.frzlyv.transactions.auth.RegisterDto;

/**
 * TestDataUtil
 */
public class TestAuthenticationDataUtil {

  private static String validMail = "test@mail.com";
  private static String validPassword = "12345678";
  private static String validRole = "USER";
  private static String invalidMail = "test@gmail.com";
  private static String invalidPassword = "1234567";

  public static RegisterDto createValidRegisterInput() {
    return RegisterDto.builder()
        .email(validMail)
        .password(validPassword)
        .role(validRole)
        .build();
  }

  public static RegisterDto createRegisterInputPasswordLessThan8Chars() {
    return RegisterDto.builder()
        .email(validMail)
        .password(invalidPassword)
        .role(validRole)
        .build();
  }

  public static LoginDto createValidLoginInput() {
    return LoginDto.builder()
        .email(validMail)
        .password(validPassword)
        .build();
  }

  public static LoginDto createInvalidPasswordLoginInput() {
    return LoginDto.builder()
        .email(validMail)
        .password(invalidPassword)
        .build();
  }

  public static LoginDto createInvalidEmailLoginInput() {
    return LoginDto.builder()
        .email(invalidMail)
        .password(validPassword)
        .build();
  }

}
