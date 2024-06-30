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
	@Column(name = "login_id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;

	@Column(unique = true, name = "login_name")
	private String loginId;

	private String password;

	@Comment("마지막 로그인 시간 기록")
	private LocalDateTime lastLoginAt;

	public void updateLastLoginAt() {
		this.lastLoginAt = LocalDateTime.now();
	}

	//비밀번호 암호화
	public Login hashPassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
		return this;
	}

	//비밀번호 확인
	public boolean checkPassword(String originalPassword, PasswordEncoder passwordEncoder) {
		return passwordEncoder.matches(originalPassword, this.password);
	}


	// INFO: 양방향 관계: 엔티티 간의 양방향 관계를 설정할 때는 양쪽 엔티티 모두 상대방을 참조하도록 해야 합니다.
	public void associateMember(Member member) {
		this.member = member;
		if(member==null) {
			throw new RuntimeException("Member is Null");
		}
	}

	public void registerDate() {
		this.lastLoginAt = LocalDateTime.now();
	}
}
