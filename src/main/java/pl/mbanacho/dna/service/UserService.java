package pl.mbanacho.dna.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mbanacho.dna.controller.dto.UserDto;
import pl.mbanacho.dna.repository.User;
import pl.mbanacho.dna.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto addNewUser(UserDto userDto) {
        User userToSave = userMapper.mapUser(userDto);
        userToSave.setCreationDate(LocalDateTime.now());
        return userMapper.mapUser(userRepository.save(userToSave));
    }
}
