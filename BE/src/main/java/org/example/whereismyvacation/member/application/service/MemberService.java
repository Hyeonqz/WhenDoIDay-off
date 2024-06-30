package org.example.whereismyvacation.member.application.service;

import java.time.LocalDateTime;

import org.example.whereismyvacation.login.application.dto.LoginRequest;
import org.example.whereismyvacation.login.domain.Login;
import org.example.whereismyvacation.login.repository.LoginRepository;
import org.example.whereismyvacation.member.application.dto.request.MemberRegisterRegister;
import org.example.whereismyvacation.member.application.dto.response.MemberRegisterResponse;
import org.example.whereismyvacation.member.domain.Member;
import org.example.whereismyvacation.member.repository.MemberRepository;
import org.example.whereismyvacation.vacation.application.dto.req.VacationDto;
import org.example.whereismyvacation.vacation.domain.Vacation;
import org.example.whereismyvacation.vacation.repository.VacationRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
	private final MemberRepository memberRepository;
	private final LoginRepository loginRepository;
	private final VacationRepository vacationRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public MemberRegisterResponse registerMember(MemberRegisterRegister memberRegisterRegister) {
		// 더티 체킹 때문에 로그인 객체가 먼저 save
		Login login = LoginRequest.toEntity(memberRegisterRegister.getLogin());
		login.registerDate(); // 필드 상태가 변경됨 -> 더티체킹 나올만함.
		login.hashPassword(passwordEncoder);
		log.info("[Password : {}", login.getPassword());

		Member member = MemberRegisterRegister.toEntity(memberRegisterRegister);
		member.registerDate();

		login.associateMember(member); // 객체 상태가 다 변경되고 연관관계 설정함

		Vacation vacation = VacationDto.toEntity(memberRegisterRegister.getVacation());
		vacation.associationMember(member);

		memberRepository.save(member);
		vacationRepository.save(vacation);
		loginRepository.save(login);

		return MemberRegisterResponse.builder()
			.id(member.getId())
			.name(member.getName())
			.age(member.getAge())
			.img(member.getImg())
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.vacation(memberRegisterRegister.getVacation())
			.build();
	}
}
