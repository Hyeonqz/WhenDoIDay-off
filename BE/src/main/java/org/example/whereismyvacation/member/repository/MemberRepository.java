package org.example.whereismyvacation.member.repository;

import org.example.whereismyvacation.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
	Member findByName(String name);
}
