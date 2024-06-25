package org.example.whereismyvacation.login.domain;

import java.time.LocalDateTime;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity(name="login")
public class Login {

	@JsonIgnore
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;

	@Column(unique = true)
	private String login;

	private String password;

	@JsonIgnore
	@Comment("마지막 로그인 시간 기록")
	private LocalDateTime lastLoginAt;

	public void associateMember(Member member) {
		this.member = member;
	}

	public void registerDate() {
		this.lastLoginAt = LocalDateTime.now();
	}
}
