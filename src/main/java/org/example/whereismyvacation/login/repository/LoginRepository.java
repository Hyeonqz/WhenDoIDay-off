package org.example.whereismyvacation.login.repository;

import org.example.whereismyvacation.login.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
