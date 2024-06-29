package org.example.whereismyvacation.login.application;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	@Value("${token.secret.key}")
	private String secretKey;

	@Value("${token.access-token.plus-hour}")
	private long accessTokenValidityInHours;

	@Value("${token.refresh-token.plus-hour}")
	private long refreshTokenValidityInHours;

	public String generateAccessToken(UserDetails userDetails) {
		return generateToken(userDetails, accessTokenValidityInHours);
	}

	public String generateRefreshToken(UserDetails userDetails) {
		return generateToken(userDetails, refreshTokenValidityInHours);
	}

	private String generateToken(UserDetails userDetails, long validityInHours) {
		SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

		Map<String, Object> claims = new HashMap<>();
		// (Optional) Custom claims 추가 가능

		return Jwts.builder()
			.setClaims(claims)
			.setSubject(userDetails.getUsername())
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + validityInHours * 3600000))
			.signWith(key)
			.compact();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		Date expiration = extractExpiration(token);
		return expiration.before(new Date());
	}

	private Date extractExpiration(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(secretKey.getBytes())
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getExpiration();
	}

	public String extractUsername(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(secretKey.getBytes())
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

}
