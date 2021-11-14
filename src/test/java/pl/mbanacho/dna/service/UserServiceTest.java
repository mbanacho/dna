package pl.mbanacho.dna.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.mbanacho.dna.controller.dto.UserDto;
import pl.mbanacho.dna.repository.User;
import pl.mbanacho.dna.repository.UserRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    UserRepository userRepository = Mockito.mock(UserRepository.class);

    UserService testedClass = new UserService(userRepository, new UserMapper());

    @Test
    void addNewUser_shouldCallSaveMethodOneTime() {
        //GIVEN
        String password = "password";
        String login = "login";
        String name = "name";
        LocalDateTime time = LocalDateTime.now();

        UserDto userDto = new UserDto(login, password, name, time);
        User user = new User();
        user.setCreationDate(time);
        user.setPassword(password);
        user.setLogin(login);
        user.setName(name);

        when(userRepository.save(any())).thenReturn(user);
        //WHEN
        UserDto savedUser = testedClass.addNewUser(userDto);
        //THEN
        verify(userRepository, times(1)).save(any());
        assertThat(savedUser).isEqualTo(userDto);
    }
}