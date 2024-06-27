package org.example.whereismyvacation.login.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.example.whereismyvacation.member.domain.Member;
import org.hibernate.annotations.Comment;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

	@NotBlank(message = "아이디는 필수 입력 값 입니다.")
	@Column(unique = true)
	private String login;

	@NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
/*	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
		message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")*/
	private String password;

	@JsonIgnore
	@Comment("마지막 로그인 시간 기록")
	private LocalDateTime lastLoginAt;

	//비밀번호 암호화
	public Login hashPassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
		return this;
	}

	//비밀번호 확인
	public boolean checkPassword(String originalPassword, PasswordEncoder passwordEncoder) {
		return passwordEncoder.matches(originalPassword, this.password);
	}


	public void associateMember(Member member) {
		this.member = member;
	}

	public void registerDate() {
		this.lastLoginAt = LocalDateTime.now();
	}
}
