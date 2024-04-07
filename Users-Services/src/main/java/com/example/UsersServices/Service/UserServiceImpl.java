package com.example.UsersServices.Service;

import com.example.UsersServices.Entity.DTO.UserRequestDto;
import com.example.UsersServices.Entity.DTO.UserResponseDto;
import com.example.UsersServices.Enum.ExceptionMessage;
import com.example.UsersServices.Exception.UserInputNotValidException;
import com.example.UsersServices.Exception.UserNotFoundException;
import com.example.UsersServices.RegistrationValidatorClient.RegistrationValidatorUser;
import com.example.UsersServices.RegistrationValidatorClient.ValidationResult;
import com.example.UsersServices.Repository.UserRepository;
import com.example.UsersServices.Utilis.MappingProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

   public List<UserResponseDto> getAllUsers(){
       return userRepository.findAll().stream()
               .map(MappingProfile::mapToUserDto).toList();
   }
    @Override
    public Object getUserById(Long id) throws UserNotFoundException {
       var user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND.getMessage()));
       return new Object(){
           public Long id = user.getId();
           public String fullName = user.getLastName().toUpperCase() +" , " + user.getFirstName();
           public String email = user.getEmail();
           public String genre = user.getGenre();
           public String phone = user.getPhone();
           public String password = user.getPassword();
       };
   }
    @Override
    public UserResponseDto addUser (UserRequestDto userDto) {
       if ( RegistrationValidatorUser.isEmailValid()
               .and(RegistrationValidatorUser.firstNameIsValid())
               .and(RegistrationValidatorUser.genreIsValid())
               .and(RegistrationValidatorUser.phoneIsValid())
               .and(RegistrationValidatorUser.passwordIsValid())
               .and(RegistrationValidatorUser.lastNameIsValid()).apply(userDto) != ValidationResult.SUCCESS)
           throw new UserInputNotValidException(
                   ExceptionMessage.USER_INPUT_NOT_VALID.getMessage());
       var user = MappingProfile.mapToUserEntity(userDto);
       return MappingProfile.mapToUserDto(userRepository.save(user));
   }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userDto) throws UserNotFoundException {
        if ( RegistrationValidatorUser.isEmailValid()
                .and(RegistrationValidatorUser.firstNameIsValid())
                .and(RegistrationValidatorUser.genreIsValid())
                .and(RegistrationValidatorUser.phoneIsValid())
                .and(RegistrationValidatorUser.passwordIsValid())
                .and(RegistrationValidatorUser.lastNameIsValid()).apply(userDto) != ValidationResult.SUCCESS)
            throw new UserInputNotValidException(
                    ExceptionMessage.USER_INPUT_NOT_VALID.getMessage());
        userDto.setId(id);
        var user = MappingProfile.mapToUserEntity(userDto);
        return MappingProfile.mapToUserDto(userRepository.save(user));
    }
    @Override
    public UserResponseDto createUser(UserRequestDto userDto) throws UserInputNotValidException {

       if (RegistrationValidatorUser.isEmailValid()
               .and(RegistrationValidatorUser.firstNameIsValid())
               .and(RegistrationValidatorUser.genreIsValid())
               .and(RegistrationValidatorUser.phoneIsValid())
               .and (RegistrationValidatorUser.passwordIsValid() )
               .and(RegistrationValidatorUser.lastNameIsValid()).apply(userDto) != ValidationResult.SUCCESS)
           throw new UserInputNotValidException(
                   ExceptionMessage.USER_INPUT_NOT_VALID.getMessage());
       var user = MappingProfile.mapToUserEntity(userDto);
       return MappingProfile.mapToUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUser (Long id) throws UserNotFoundException {
       userRepository.deleteById(id);
   }
    public UserResponseDto getByUserLastName(String username) throws UserNotFoundException {
      var user = userRepository.findByLastName(username).orElseThrow();
      return MappingProfile.mapToUserDto(user);
   }

    public UserResponseDto getUserByEmail(String email) throws UserNotFoundException {
      var user = userRepository.findByEmail(email).orElseThrow();
      return MappingProfile.mapToUserDto(user);
    }

}
