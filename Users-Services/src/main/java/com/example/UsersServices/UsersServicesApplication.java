package com.example.UsersServices;

import com.example.UsersServices.Entity.DTO.UserRequestDto;
import com.example.UsersServices.Entity.DTO.UserResponseDto;
import com.example.UsersServices.Entity.User;
import com.example.UsersServices.Enum.UserRole;
import com.example.UsersServices.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@EnableFeignClients
@AllArgsConstructor
@SpringBootApplication
public class UsersServicesApplication {
	private final UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(UsersServicesApplication.class, args);
	}
	@Bean
	CommandLineRunner stat(){
		return args -> {
			UserRequestDto user  = new UserRequestDto(1L,"rim","test","egeh"
					,"rim:gharib@gmail.com", "gefjghpui","CUSTOMER");
			userService.addUser(user);
		};
	}
}
