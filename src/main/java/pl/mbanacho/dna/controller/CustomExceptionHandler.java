package pl.mbanacho.dna.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.mbanacho.dna.controller.dto.ExceptionDto;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, ConstraintViolationException.class})
    public final ResponseEntity<ExceptionDto> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(new ExceptionDto(ex.getMessage()));
    }
}