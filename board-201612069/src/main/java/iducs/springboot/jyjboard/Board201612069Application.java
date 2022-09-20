package iducs.springboot.jyjboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EnableJpaAuditing //AuditionEntityListner를 활성화하는 애노테이션, 모니터링보다 강도가 높은다는 뜻..
public class Board201612069Application {

	public static void main(String[] args) {
		SpringApplication.run(Board201612069Application.class, args);
	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() { // put, delete
		return new HiddenHttpMethodFilter();
	}
}
