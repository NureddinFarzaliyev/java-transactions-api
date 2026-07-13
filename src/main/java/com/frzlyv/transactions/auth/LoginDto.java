package com.frzlyv.transactions.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginDto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
  @NotBlank(message = "Email cannot be empty")
  private String email;
  @NotBlank(message = "Password cannot be empty")
  private String password;
}
