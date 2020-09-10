package com.softserve.marathon.exception.handler;

import com.softserve.marathon.exception.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleRuntimeException() {
        log.error("some problem");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("some problem");
    }

    @ExceptionHandler(BadEmailOrPasswordException.class)
    public final ResponseEntity<String> handleBadEmailOrPassword() {
        log.error("bad email or password");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad email or password");
    }

    @ExceptionHandler(MarathonNotFoundByIdException.class)
    public final ResponseEntity<String> handleMarathonNotFoundById() {
        log.error("no such marathon");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no such marathon");
    }

    @ExceptionHandler(ProgressNotFoundByIdException.class)
    public final ResponseEntity<String> handleProgressNotFoundById() {
        log.error("no such progress");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no such progress");
    }

    @ExceptionHandler(SprintNotFoundByIdException.class)
    public final ResponseEntity<String> handleSprintNotFoundById() {
        log.error("no such sprint");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no such sprint");
    }

    @ExceptionHandler({TaskNotAddedToSprintException.class, TaskNotFoundByIdException.class})
    public final ResponseEntity<String> handleTaskExceptions() {
        log.error("no such task");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no such task");
    }

    @ExceptionHandler(UserNotFoundByIdException.class)
    public final ResponseEntity<String> handleUserNotFoundById() {
        log.error("no such user");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no such user");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handle404() {
        log.error("404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no such page");
    }

}
