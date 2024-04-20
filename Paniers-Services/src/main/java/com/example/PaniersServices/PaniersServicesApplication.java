package com.example.PaniersServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PaniersServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaniersServicesApplication.class, args);
	}

}
