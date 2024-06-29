package org.example.whereismyvacation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenErrorCode {
	INVALID_TOKEN(500,2000,"유효하지 않은 토큰 입니다"),
	EXPIRED_TOKEN(500,2001,"만료된 토큰 입니다."),
	TOKEN_EXCEPTION(500,2002,"알 수 없는 토큰 에러 입니다.");

	private final Integer httpStatusCode;
	private final Integer errorCode;
	private final String errorMessage;
}
