package com.example.projectportal;

import org.springframework.boot.SpringApplication;

public class TestProjectportalApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProjectportalApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
