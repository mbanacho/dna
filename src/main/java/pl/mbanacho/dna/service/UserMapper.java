package pl.mbanacho.dna.service;

import org.springframework.stereotype.Service;
import pl.mbanacho.dna.controller.dto.UserDto;
import pl.mbanacho.dna.repository.User;

@Service
public class UserMapper {

    public UserDto mapUser(User user) {
        return new UserDto(user.getLogin(), user.getPassword(), user.getName(), user.getCreationDate());
    }

    public User mapUser(UserDto userDto) {
        User user = new User();
        user.setLogin(userDto.login());
        user.setPassword(userDto.password());
        user.setName(userDto.name());
        return user;
    }
}
