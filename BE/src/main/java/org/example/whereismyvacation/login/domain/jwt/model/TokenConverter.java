package org.example.whereismyvacation.login.domain.jwt.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.example.whereismyvacation.login.domain.jwt.helper.JwtTokenHelper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TokenConverter {
	private final JwtTokenHelper jwtTokenHelper;

	public TokenDto issueAccessToken(Long userId) {
		HashMap<String, Object> data = new HashMap<>();
		data.put("userId",userId);
		return jwtTokenHelper.issueAccessToken(data);
	}

	public TokenDto refreshAccessToken(Long userId) {
		HashMap<String, Object> data = new HashMap<>();
		data.put("userId",userId);
		return jwtTokenHelper.issueRefreshToken(data);
	}

	public Long validationToken(String token) {
		Map<String, Object> stringObjectMap = jwtTokenHelper.validationTokenWithThrow(token);
		var userId = (Long) stringObjectMap.get("userId");
		Objects.requireNonNull(userId, "userId is Null");
		return Long.parseLong(userId.toString());
	}
}
