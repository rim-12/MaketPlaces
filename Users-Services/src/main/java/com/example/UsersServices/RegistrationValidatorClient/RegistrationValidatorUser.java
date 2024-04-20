package com.example.UsersServices.RegistrationValidatorClient;

import com.example.UsersServices.Entity.DTO.UserRequestDto;

import java.util.function.Function;

import static com.example.UsersServices.RegistrationValidatorClient.ValidationResult.*;

public interface RegistrationValidatorUser extends Function<UserRequestDto,ValidationResult> {
    static RegistrationValidatorUser isEmailValid(){
        return user -> (user.getEmail().contains("@")
                && user.getEmail().endsWith("gmail.com")
                && !(user.getEmail().startsWith("@"))) ?
                SUCCESS : EMAIL_NOT_VALID;
    }
    static RegistrationValidatorUser firstNameIsValid() {
        return user ->!(user.getFirstName().isEmpty()) ?
                SUCCESS : FIRST_NAME_IS_NULL;
    }
    static RegistrationValidatorUser lastNameIsValid() {
        return user ->!(user.getLastName().isEmpty())?
                SUCCESS : LAST_NAME_IS_NULL;
    }
    static RegistrationValidatorUser genreIsValid() {
        return user ->!(user.getGenre().isEmpty())?
                SUCCESS : GENRE_IS_NULL;
    }
    static RegistrationValidatorUser phoneIsValid(){
        return user ->!(user.getGenre().isEmpty())?
                SUCCESS : PHONE_IS_NULL;
    }
    static RegistrationValidatorUser passwordIsValid() {
        return user ->!(user.getPassword().isEmpty())?
                SUCCESS : PASSWORD_IS_NULL;
    }
    default RegistrationValidatorUser and(RegistrationValidatorUser other) {
        return user ->{
            ValidationResult result = this.apply(user);
            return result.equals(SUCCESS) ? other.apply(user) : result;
        };
    }
}
