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

        if (ex instanceof IllegalArgumentException illegalArgumentException) {
            return ResponseEntity.badRequest().body(new ExceptionDto(illegalArgumentException.getMessage()));
        } else if (ex instanceof ConstraintViolationException constraintViolationException) {
            return ResponseEntity.badRequest().body(new ExceptionDto(constraintViolationException.getMessage()));
        } else {
            return ResponseEntity.badRequest().body(new ExceptionDto(ex.getMessage()));
        }
    }
}
