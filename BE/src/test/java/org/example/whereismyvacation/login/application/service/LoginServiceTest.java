package org.example.whereismyvacation.login.application.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.whereismyvacation.login.application.dto.LoginRequest;
import org.example.whereismyvacation.login.application.dto.TokenResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginServiceTest {

	@Autowired
	private LoginService loginService;

	@Test
	@DisplayName("로그인시 토큰 발급 테스트")
	void 로그인_토큰_발급_테스트() {
	    // given
		LoginRequest loginRequest = LoginRequest.builder()
				.loginId("jin@naver.com")
				.password("1234")
				.build();

	    // when
		TokenResponse tokenResponse = loginService.userLogin(loginRequest);

		// then
		assertThat(tokenResponse).isNotNull();
		assertThat(tokenResponse.getAccessToken()).isNotEmpty();
		assertThat(tokenResponse.getRefreshToken()).isNotEmpty();

	}

}