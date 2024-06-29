package org.example.whereismyvacation.login.ui;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.whereismyvacation.login.application.dto.LoginRequest;
import org.example.whereismyvacation.login.application.dto.TokenResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("로그인시 토큰 발급 테스트")
	void 로그인_토큰_발급_테스트() throws Exception {
		// given
		LoginRequest loginRequest = LoginRequest.builder()
			.loginId("jin@naver.com")
			.password("1234")
			.build();

		// when & then
		mockMvc.perform(post("/api/v1/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(loginRequest)))
			.andExpect(status().isOk())
			.andExpect(result -> {
				String response = result.getResponse().getContentAsString();
				TokenResponse tokenResponse = objectMapper.readValue(response, TokenResponse.class);
				assertThat(tokenResponse).isNotNull();
				assertThat(tokenResponse.getAccessToken()).isNotEmpty();
				assertThat(tokenResponse.getRefreshToken()).isNotEmpty();
			});
	}
}