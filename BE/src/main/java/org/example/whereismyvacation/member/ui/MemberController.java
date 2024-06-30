package org.example.whereismyvacation.member.ui;

import org.example.whereismyvacation.member.application.dto.request.MemberRegisterRegister;
import org.example.whereismyvacation.member.application.dto.response.MemberRegisterResponse;
import org.example.whereismyvacation.member.application.service.MemberService;
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
	public ResponseEntity<MemberRegisterResponse> register(@RequestBody @Valid MemberRegisterRegister member) {
		MemberRegisterResponse memberResResponse = memberService.registerMember(member);
		return ResponseEntity.ok(memberResResponse);
	}
}
