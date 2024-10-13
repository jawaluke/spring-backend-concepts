package org.learn.SpringBootWorkAroundBranch.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BaseControllerAdvice {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleIllegalArguement(IllegalArgumentException ex) {
        log.error("IllegalArgumentException : {}",ex.getMessage());
        return ResponseEntity.internalServerError().body("Something went wrong");
    }

}
