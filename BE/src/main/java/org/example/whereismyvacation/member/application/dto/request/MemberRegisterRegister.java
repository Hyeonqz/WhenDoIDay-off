package org.example.whereismyvacation.member.application.dto.request;

import org.example.whereismyvacation.login.application.dto.LoginRequest;
import org.example.whereismyvacation.member.domain.Member;
import org.example.whereismyvacation.vacation.application.dto.req.VacationDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Getter
@Builder
public class MemberRegisterRegister {

	@NotNull
	private LoginRequest login;
	@NotNull
	private VacationDto vacation;
	@NotNull
	private String name;
	@NotNull
	private Integer age;
	@NotNull
	private String img;

	public static Member toEntity(MemberRegisterRegister memberRegisterRegister) {
		return Member.builder()
			.name(memberRegisterRegister.getName())
			.age(memberRegisterRegister.getAge())
			.img(memberRegisterRegister.getImg())
			.build();
	}
}
