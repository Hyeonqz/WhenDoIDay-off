package org.example.whereismyvacation.login.application.service;

import java.util.Optional;

import org.example.whereismyvacation.exception.TokenErrorCode;
import org.example.whereismyvacation.login.application.dto.TokenResponse;
import org.example.whereismyvacation.login.domain.Login;
import org.example.whereismyvacation.login.domain.jwt.model.TokenConverter;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class TokenService {
	private final TokenConverter tokenConverter;

	public TokenResponse issueToken(Login login) {
		return Optional.ofNullable(login)
			// TODO: 이렇게하면 나중에 조인할 때 문제 생길수도?? .map(Login::getId) -> member_id 가 아니라서 혹시라도
			.map(Login::getId)
			.map(memid -> {
				var accessToken = tokenConverter.issueAccessToken(memid);
				var refreshToken = tokenConverter.refreshAccessToken(memid);
				return TokenResponse.toResponse(accessToken,refreshToken);
			})
			.orElseThrow( () -> new RuntimeException(TokenErrorCode.TOKEN_EXCEPTION.getErrorMessage()));
	}

}
