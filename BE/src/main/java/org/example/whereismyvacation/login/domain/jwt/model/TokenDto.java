package org.example.whereismyvacation.login.domain.jwt.model;

import java.time.LocalDateTime;
import java.util.Objects;

import org.example.whereismyvacation.login.application.dto.TokenResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class TokenDto {

	private String token;
	private LocalDateTime expiredAt;


}
