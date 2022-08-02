package com.codeIT.moonCalendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MoonCalendarApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoonCalendarApplication.class, args);
	}

}