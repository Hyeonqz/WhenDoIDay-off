package org.example.whereismyvacation.login.repository;

import java.util.Optional;

import org.example.whereismyvacation.login.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
	// Optional 은 객체를 파라미터로 받을 때 많이 샤용하는 경향이 있는 듯?
	Login findByLoginId(String loginId);

	Login findFirstByLoginIdAndPassword(String loginId, String password);
}
