package com.frzlyv.transactions.features;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.frzlyv.transactions.auth.LoginDto;
import com.frzlyv.transactions.auth.RegisterDto;
import com.frzlyv.transactions.user.UserService;
import com.frzlyv.transactions.utils.TestAuthenticationDataUtil;

import tools.jackson.databind.ObjectMapper;

/**
 * AuthenticationTests
 */
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthenticationTests {

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;
  private UserService userService;

  public AuthenticationTests(MockMvc mockMvc, ObjectMapper objectMapper, UserService userService) {
    this.mockMvc = mockMvc;
    this.objectMapper = objectMapper;
    this.userService = userService;
  }

  private void registerValidUser() {
    RegisterDto registerDto = TestAuthenticationDataUtil.createValidRegisterInput();
    userService.register(registerDto);
  }

  @Test
  public void testThatSuccessfulRegisterReturns201() throws Exception {
    RegisterDto registerDto = TestAuthenticationDataUtil.createValidRegisterInput();
    String jsonRegisterDto = objectMapper.writeValueAsString(registerDto);

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRegisterDto))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  };

  @Test
  public void testThatSuccessfulRegisterCreatesUser() throws Exception {
    RegisterDto registerDto = TestAuthenticationDataUtil.createValidRegisterInput();
    String jsonRegisterDto = objectMapper.writeValueAsString(registerDto);

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRegisterDto))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(registerDto.getEmail()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.role").value(registerDto.getRole()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber());
  };

  @Test
  public void testThatRegisterWithSameEmailReturns409() throws Exception {
    registerValidUser();

    RegisterDto registerDto = TestAuthenticationDataUtil.createValidRegisterInput();
    String jsonRegisterDto = objectMapper.writeValueAsString(registerDto);

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRegisterDto))
        .andExpect(MockMvcResultMatchers.status().isConflict());
  };

  @Test
  public void testThatRegisterWithPasswordLessThan8CharactersReturns400() throws Exception {
    RegisterDto registerDto = TestAuthenticationDataUtil.createRegisterInputPasswordLessThan8Chars();
    String jsonRegisterDto = objectMapper.writeValueAsString(registerDto);

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/v1/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRegisterDto))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  };

  @Test
  public void testThatSuccessfulLoginReturns200() throws Exception {
    registerValidUser();

    LoginDto loginDto = TestAuthenticationDataUtil.createValidLoginInput();
    String jsonLoginDto = objectMapper.writeValueAsString(loginDto);

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonLoginDto))
        .andExpect(MockMvcResultMatchers.status().isOk());
  };

  @Test
  public void testThatSuccessfulLoginReturnsToken() throws Exception {
    registerValidUser();

    LoginDto loginDto = TestAuthenticationDataUtil.createValidLoginInput();
    String jsonLoginDto = objectMapper.writeValueAsString(loginDto);

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonLoginDto))
        .andExpect(MockMvcResultMatchers.jsonPath("$.token").isNotEmpty());
  };

  @Test
  public void testThatWrongEmailLoginReturns400() throws Exception {
    registerValidUser();

    LoginDto loginDto = TestAuthenticationDataUtil.createInvalidEmailLoginInput();
    String jsonLoginDto = objectMapper.writeValueAsString(loginDto);

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonLoginDto))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  };

  @Test
  public void testThatWrongPasswordLoginReturns400() throws Exception {
    registerValidUser();

    LoginDto loginDto = TestAuthenticationDataUtil.createInvalidPasswordLoginInput();
    String jsonLoginDto = objectMapper.writeValueAsString(loginDto);

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonLoginDto))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  };
}
