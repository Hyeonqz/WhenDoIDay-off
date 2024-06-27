package org.example.whereismyvacation.member.ui;

import org.example.whereismyvacation.member.application.dto.request.MemberReqRegister;
import org.example.whereismyvacation.member.application.dto.response.MemberResResponse;
import org.example.whereismyvacation.member.application.service.MemberService;
import org.example.whereismyvacation.member.domain.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class MemberController {
	private final MemberService memberService;

	@PostMapping("/register")
	public ResponseEntity<MemberResResponse> register(@RequestBody @Valid MemberReqRegister member) {
		MemberResResponse memberResResponse = memberService.registerMember(member);
		return ResponseEntity.ok(memberResResponse);
	}
}
