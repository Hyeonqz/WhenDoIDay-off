package org.example.whereismyvacation.member.ui;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.example.whereismyvacation.login.domain.Login;
import org.example.whereismyvacation.member.application.service.MemberService;
import org.example.whereismyvacation.member.domain.Member;
import org.example.whereismyvacation.member.repository.MemberRepository;
import org.example.whereismyvacation.vacation.domain.Vacation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	@DisplayName("회원가입 테스트")
	void MemberControllerTest() throws Exception {
	    // given
		Vacation vacation = Vacation.builder()
			.totalVacation(15)
			.remainingVacation(14)
			.useVacation(1)
			.build();

		Login login = Login.builder()
			.loginId("wlsgusrb78")
			.password("1234")
			.lastLoginAt(LocalDateTime.now())
			.build();

		Member member = Member.builder()
			.id(1L)
			.name("jin")
			.age(26)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.img("asd.img")
			.vacation(vacation)
			.login(login)
			.build();

	    // when
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/member/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(member)))
			.andExpect(status().isOk());

	    //
		Member savedMember = memberRepository.findByName("jin");
		assertNotNull(savedMember);
		assertEquals("jin", savedMember.getName());
		assertEquals(26, savedMember.getAge());
	}

}