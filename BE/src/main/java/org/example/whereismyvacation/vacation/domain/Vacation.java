package org.example.whereismyvacation.vacation.domain;

import org.example.whereismyvacation.member.domain.Member;
import org.hibernate.annotations.Comment;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Vacation {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="vacation_id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;

	@Comment("총 휴가")
	@Column(length = 10)
	private Integer totalVacation;

	@Comment("현재 까지 사용 휴가")
	@Column(length = 10)
	private Integer useVacation;

	@Comment("잔여 휴가")
	@Column(length = 10)
	private Integer remainingVacation;

	//연관관계 메소드 -> 안하면 member_id 안들어 감.
	public void associationMember(Member member) {
		this.member = member;
		if(member == null) {
			throw new RuntimeException("Member is Null");
		}
	}
}
