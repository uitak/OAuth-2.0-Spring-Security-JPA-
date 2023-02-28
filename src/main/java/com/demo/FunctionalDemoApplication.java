package com.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FunctionalDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunctionalDemoApplication.class, args);
	}

	//@Autowired
	//private ApplicationContext ac;
	
	/*
	// 스프링 컨테이너에 등록된 모든 빈 출력.
	@Bean
	public CommandLineRunner printBeans() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				String[] beans = ac.getBeanDefinitionNames();
				
				for (String bean : beans) {
					System.out.println(bean);
				}
			}
		};
		
	}
	*/

}
