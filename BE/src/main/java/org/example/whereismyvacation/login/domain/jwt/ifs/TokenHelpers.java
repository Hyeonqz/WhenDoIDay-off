package org.example.whereismyvacation.login.domain.jwt.ifs;

import java.util.Map;

import org.example.whereismyvacation.login.domain.jwt.model.TokenDto;

public interface TokenHelpers {
	TokenDto issueAccessToken(Map<String,Object> data);
	TokenDto issueRefreshToken(Map<String,Object> data);

	Map<String,Object> validationTokenWithThrow(String token);
}
