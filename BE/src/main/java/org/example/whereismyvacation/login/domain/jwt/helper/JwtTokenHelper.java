package org.example.whereismyvacation.login.domain.jwt.helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.example.whereismyvacation.exception.TokenErrorCode;
import org.example.whereismyvacation.login.domain.jwt.ifs.TokenHelpers;
import org.example.whereismyvacation.login.domain.jwt.model.TokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtTokenHelper implements TokenHelpers {

	@Value("${token.secret.key}")
	private String secretKey;

	@Value("${token.access-token.plus-hour}")
	private long accessTokenValidityInHours;

	@Value("${token.refresh-token.plus-hour}")
	private long refreshTokenValidityInHours;

	@Override
	public TokenDto issueAccessToken (Map<String, Object> data) {
		var expiredTime = LocalDateTime.now().plusHours(accessTokenValidityInHours);
		var expiredAt = Date.from(
			expiredTime.atZone(ZoneId.systemDefault()).toInstant());

		var key = Keys.hmacShaKeyFor(secretKey.getBytes());

		var jwtToken = Jwts.builder()
			.signWith(key, SignatureAlgorithm.HS256)
			.setClaims(data)
			.setExpiration(expiredAt)
			.compact();

		return TokenDto.builder()
			.token(jwtToken)
			.expiredAt(expiredTime)
			.build();
	}

	@Override
	public TokenDto issueRefreshToken (Map<String, Object> data) {
		var expiredTime = LocalDateTime.now().plusHours(refreshTokenValidityInHours);
		var expiredAt = Date.from(
			expiredTime.atZone(ZoneId.systemDefault()).toInstant());

		var key = Keys.hmacShaKeyFor(secretKey.getBytes());

		var jwtToken = Jwts.builder()
			.signWith(key, SignatureAlgorithm.HS256)
			.setClaims(data)
			.setExpiration(expiredAt)
			.compact();

		return TokenDto.builder()
			.token(jwtToken)
			.expiredAt(expiredTime)
			.build();
	}

	@Override
	public Map<String, Object> validationTokenWithThrow (String token) {
		var key = Keys.hmacShaKeyFor(secretKey.getBytes());
		var parser = Jwts.parserBuilder()
			.setSigningKey(key)
			.build();

		try {
			Jws<Claims> claimsJws = parser.parseClaimsJws(token);// 파싱
			return new HashMap<String,Object>(claimsJws.getBody());

		} catch (Exception e) {
			if(e instanceof SignatureException) {
				// 유효하지 않은 토큰
				throw new IllegalArgumentException(TokenErrorCode.INVALID_TOKEN.toString(), e);
			}
			if(e instanceof ExpiredJwtException) {
				// 만료된 토큰
				throw new RuntimeException(TokenErrorCode.EXPIRED_TOKEN.toString(), e);
			} else {
				throw new RuntimeException(TokenErrorCode.TOKEN_EXCEPTION.getErrorMessage(), e);
			}
		}
	}

}
