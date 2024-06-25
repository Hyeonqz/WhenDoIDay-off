package org.example.whereismyvacation.member.application.dto.request;

import org.example.whereismyvacation.login.domain.Login;
import org.example.whereismyvacation.member.domain.Member;
import org.example.whereismyvacation.vacation.application.dto.req.VacationDto;
import org.example.whereismyvacation.vacation.domain.Vacation;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Getter
@Builder
public class MemberReqRegister {

	@NotNull
	private Login login;
	@NotNull
	private String name;
	@NotNull
	private Integer age;
	@NotNull
	private VacationDto vacation;
	@NotNull
	private String img;

	public static Member toEntity(MemberReqRegister memberReqRegister) {
		return Member.builder()
			.name(memberReqRegister.getName())
			.age(memberReqRegister.getAge())
			.login(memberReqRegister.getLogin())
			.img(memberReqRegister.getImg())
			.build();
	}
}
