package org.example.whereismyvacation.login.application.dto;

import org.example.whereismyvacation.login.domain.Login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Getter
@Builder
public class LoginRequest {

	@NotBlank(message = "아이디는 필수 값 입니다.")
	@Email(message = "이메일 형식을 아이디를 해주세요")
	private String loginId;

	@NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
		message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
	private String password;

	public static Login toEntity(LoginRequest loginRequest) {
		return Login.builder()
			.loginId(loginRequest.getLoginId())
			.password(loginRequest.getPassword())
			.build();
	}
}
