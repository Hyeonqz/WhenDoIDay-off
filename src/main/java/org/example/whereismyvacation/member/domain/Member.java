package org.example.whereismyvacation.member.domain;

import java.time.LocalDateTime;

import org.example.whereismyvacation.login.domain.Login;
import org.example.whereismyvacation.vacation.domain.Vacation;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Immutable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Builder
@Table(name="member")
@Entity
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
	@JoinColumn(name="login_id")
	private Login login;

	@OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
	@JoinColumn(name="vacation_id")
	private Vacation vacation;

	@Column(nullable = false, length = 10)
	private String name;

	@Column(nullable = false, length = 3)
	private Integer age;

	@Comment("휴가 승인서 파일")
	// 파일 저장할 수 있게 만들기
	private String img;

	// 지금까지 올린 휴가 기록

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	public void registerDate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
}
