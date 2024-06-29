package org.example.whereismyvacation.login.application.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import org.example.whereismyvacation.login.domain.jwt.model.TokenDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
public class TokenResponse {

	private String accessToken;
	private LocalDateTime accessTokenExpiredAt;
	private String refreshToken;
	private LocalDateTime refreshTokenExpiredAt;

	public static TokenResponse toResponse(TokenDto accessToken, TokenDto refreshToken) {

		Objects.requireNonNull(accessToken.getToken(), "Access Token is null");
		Objects.requireNonNull(refreshToken.getToken(), "RefreshToken is null");

		return TokenResponse.builder()
			.accessToken(accessToken.getToken())
			.accessTokenExpiredAt(accessToken.getExpiredAt())
			.refreshToken(refreshToken.getToken())
			.refreshTokenExpiredAt(refreshToken.getExpiredAt())
			.build();
	}
}
