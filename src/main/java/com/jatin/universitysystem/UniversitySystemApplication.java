package com.jatin.universitysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@Configuration
@EnableAutoConfiguration
@ComponentScan("com.jatin")
@EnableJpaRepositories("com.jatin")
@EntityScan(basePackageClasses = {UniversitySystemApplication.class,Jsr310Converters.class})
public class UniversitySystemApplication extends SpringBootServletInitializer {
	

	public static void main(String[] args) {
		SpringApplication.run(UniversitySystemApplication.class, args);
	}
}
