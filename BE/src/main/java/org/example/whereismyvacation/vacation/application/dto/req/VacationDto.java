package org.example.whereismyvacation.vacation.application.dto.req;

import org.example.whereismyvacation.member.domain.Member;
import org.example.whereismyvacation.vacation.domain.Vacation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter
public class VacationDto {
	private Integer totalVacations;
	private Integer usedVacations;
	private Integer remainingVacations;

	public static Vacation toEntity(VacationDto vacationDto) {
		return Vacation.builder()
			.totalVacation(vacationDto.getTotalVacations())
			.useVacation(vacationDto.getUsedVacations())
			.remainingVacation(vacationDto.getRemainingVacations())
			.build();
	}
}
