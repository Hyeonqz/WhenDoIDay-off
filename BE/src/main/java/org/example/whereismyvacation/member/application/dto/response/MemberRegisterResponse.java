package org.example.whereismyvacation.member.application.dto.response;

import java.time.LocalDateTime;

import org.example.whereismyvacation.vacation.application.dto.req.VacationDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Getter
@Builder
public class MemberRegisterResponse {
	private Long id;
	private String name;
	private Integer age;
	private VacationDto vacation;
	private String img;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
