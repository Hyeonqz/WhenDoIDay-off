package org.example.whereismyvacation.member.application.service;

import java.time.LocalDateTime;

import org.example.whereismyvacation.login.domain.Login;
import org.example.whereismyvacation.login.repository.LoginRepository;
import org.example.whereismyvacation.member.application.dto.request.MemberRegisterRegister;
import org.example.whereismyvacation.member.application.dto.response.MemberRegisterResponse;
import org.example.whereismyvacation.member.repository.MemberRepository;
import org.example.whereismyvacation.vacation.application.dto.req.VacationDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MemberServiceTest {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private LoginRepository loginRepository;

	@Test
	@DisplayName("회원가입 비밀번호 암호화 테스트")
	void MemberServiceTest() {
	    // given
		MemberRegisterRegister req = creatingSignUpReqeust();

	    // when
		MemberRegisterResponse member = memberService.registerMember(req);

	    // then
	}

	private MemberRegisterRegister creatingSignUpReqeust () {
		Login login = Login.builder()
			.loginId("jin@naver.com")
			.password("1234")
			.lastLoginAt(LocalDateTime.now())
			.build();

		VacationDto vacation = VacationDto.builder()
			.totalVacations(15)
			.remainingVacations(14)
			.usedVacations(1)
			.build();

		return MemberRegisterRegister.builder()
			.vacation(vacation)
			.name("진현규")
			.age(25)
			.img("123.jpg")
			.build();
	}

}