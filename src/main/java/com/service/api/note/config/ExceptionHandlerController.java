package com.service.api.note.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@ControllerAdvice
public class ExceptionHandlerController {

    Logger logger = Logger.getLogger(ExceptionHandlerController.class.getName());
    private static final String MESSAGE = "message";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        logger.log(Level.SEVERE, ex.getMessage(), ex);
        return new ResponseEntity<>(Map.of(MESSAGE,"Unexpected error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFountException(EntityNotFoundException ex) {
        logger.log(Level.WARNING, ex.getMessage(), ex);
        return new ResponseEntity<>(Map.of(MESSAGE, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
