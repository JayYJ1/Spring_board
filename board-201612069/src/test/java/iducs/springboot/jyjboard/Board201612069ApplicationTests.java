package iducs.springboot.jyjboard;

import iducs.springboot.jyjboard.entity.MemberEntity;
import iducs.springboot.jyjboard.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
class Board201612069ApplicationTests {

	@Autowired
	MemberRepository memberRepository;

	@Test // Unit Test: JUnit 도구 활용 -> 통합 테스트(Integration Test)
	void testMemberInitialize(){
		IntStream.rangeClosed(1, 50).forEach(i ->{
			MemberEntity entity = MemberEntity.builder()
					.id("id" + i)
					.pw("pw" + i)
					.name("name" + i)
					.email("email" + i + "@induk.ac.kr")
					.phone("phone" + new Random().nextInt(50))
					.address("address" + i)
					.build();
			memberRepository.save(entity);
		});
	}
	@Test
	void testAdmin(){
		String str = "admin";
		MemberEntity entity = MemberEntity.builder()
				.id(str)
				.pw(str)
				.name("name-" + str)
				.email(str + "@induk.ac.kr")
				.phone("phone-" + new Random().nextInt(50))
				.address("address-" + new Random().nextInt(50))
				.build();
		memberRepository.save(entity);
	}
//	void contextLoads() {
//		IntStream.rangeClosed(1, 10).forEach(i -> {
//			MemberEntity member = MemberEntity.builder().id("id " + i).pw("pw " + i).name("name " + i).build();
//			memberRepository.save(member);
//		});
//	}

}
