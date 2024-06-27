package org.example.whereismyvacation.member.application.service;

import java.time.LocalDateTime;

import org.example.whereismyvacation.login.domain.Login;
import org.example.whereismyvacation.login.repository.LoginRepository;
import org.example.whereismyvacation.member.application.dto.request.MemberReqRegister;
import org.example.whereismyvacation.member.application.dto.response.MemberResResponse;
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
	public MemberResResponse registerMember(MemberReqRegister memberReqRegister) {
		Member member = MemberReqRegister.toEntity(memberReqRegister);
		member.registerDate();
		memberRepository.save(member);

		Login login = memberReqRegister.getLogin();
		login.hashPassword(passwordEncoder);
		log.info("[login.associateMember] : {}", member);
		login.registerDate();
		login.associateMember(member);
		loginRepository.save(login);

		Vacation vacation = VacationDto.toEntity(memberReqRegister.getVacation());
		vacationRepository.save(vacation);

		return MemberResResponse.builder()
			.id(member.getId())
			.name(member.getName())
			.age(member.getAge())
			.img(member.getImg())
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.vacation(memberReqRegister.getVacation())
			.build();
	}
}
