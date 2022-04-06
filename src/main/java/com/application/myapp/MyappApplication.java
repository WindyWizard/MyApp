package com.application.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.github.bufferings.thymeleaf.extras.nl2br.dialect.Nl2brDialect;

@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);
	}

	@Bean
	public Nl2brDialect dialect() {
	  return new Nl2brDialect();
	}
}
