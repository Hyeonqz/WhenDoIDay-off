package org.example.whereismyvacation.login.application.service;


import org.example.whereismyvacation.login.application.dto.LoginRequest;
import org.example.whereismyvacation.login.application.dto.TokenResponse;
import org.example.whereismyvacation.login.domain.Login;
import org.example.whereismyvacation.login.repository.LoginRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LoginService {
	private final LoginRepository loginRepository;
	private final TokenService tokenService;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public TokenResponse userLogin(LoginRequest req) {
		Login login = authenticateUser(req.getLoginId(), req.getPassword());
		login.updateLastLoginAt();
		loginRepository.save(login);
		return tokenService.issueToken(login);
	}

	private Login authenticateUser(String loginId, String password) {
		Login login = loginRepository.findByLoginId(loginId);
		if (login == null) {
			throw new UsernameNotFoundException("User Login Id Not found : " + loginId);
		}

		// 로그인 시 비밀번호를 검증하는 과정에서 비밀번호가 복호화되지 않더라도,
		// passwordEncoder.matches() 메서드를 사용하여 입력된 비밀번호를 해싱한 후 데이터베이스에 저장된 해시 값과 비교함
		// 이로 인해 비밀번호가 복호화되지 않아도 사용자는 정상적으로 로그인 가능.
		if (!passwordEncoder.matches(password, login.getPassword())) { // 복호화 로직
			throw new BadCredentialsException("Invalid password");
		}
		return login;
	}

	// TODO: 암호화 비밀번호 -> 복호화 로직도 필요할 듯
	@Transactional
	public Login login(String loginId, String password) {
		return loginRepository.findFirstByLoginIdAndPassword(loginId,password);
	}

}
