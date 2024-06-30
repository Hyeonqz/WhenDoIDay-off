package org.example.whereismyvacation;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.example.whereismyvacation.member.domain.Member;
import org.example.whereismyvacation.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WhereismyvacationApplicationTests {

	@Autowired
	private MemberRepository memberRepository;

	@Test
	void contextLoads () {
	}

	@DisplayName("더미 데이터 insert")
	@Test
	public void insertDummies() {
		IntStream.rangeClosed(1,1000000).forEach(
			i -> {
				Member member = Member.builder()
					.name("진현규"+i)
					.age(25+i)
					.img(i+".jpg")
					.createdAt(LocalDateTime.now())
					.build();
				memberRepository.save(member);
			}
		);
	}

}
