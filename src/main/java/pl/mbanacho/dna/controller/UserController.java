package pl.mbanacho.dna.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.mbanacho.dna.controller.dto.UserDto;
import pl.mbanacho.dna.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/user")
    public UserDto addUser(@RequestBody UserDto user) {
        return userService.addNewUser(user);
    }
}
