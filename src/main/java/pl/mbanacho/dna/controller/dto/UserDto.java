package pl.mbanacho.dna.controller.dto;

import java.time.LocalDateTime;

public record UserDto(String login, String password, String name, LocalDateTime creationDate) {
}
