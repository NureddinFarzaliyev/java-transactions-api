package com.frzlyv.transactions.health;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * HealthCheckTest
 */
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
@AutoConfigureMockMvc
public class HealthCheckTest {

  private MockMvc mockMvc;

  public HealthCheckTest(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @Test
  public void testThatHealthCheckReturnsUp() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.get("/actuator/health"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("UP"));
  }

}
