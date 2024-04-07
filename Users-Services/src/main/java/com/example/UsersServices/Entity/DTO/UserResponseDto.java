package com.example.UsersServices.Entity.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
 @Setter
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String genre;
    private String email;
}
