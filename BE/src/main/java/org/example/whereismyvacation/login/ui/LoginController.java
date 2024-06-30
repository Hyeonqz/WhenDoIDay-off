package org.example.whereismyvacation.login.ui;

import org.example.whereismyvacation.login.application.dto.LoginRequest;
import org.example.whereismyvacation.login.application.dto.TokenResponse;
import org.example.whereismyvacation.login.application.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class LoginController {
	private final LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		TokenResponse tokenResponse = loginService.userLogin(loginRequest);
		return ResponseEntity.ok(tokenResponse);
	}
}
