package com.farm.pedia.global.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends AbstractExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<Map<String, Object>>> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("Constraint violation exception: ", ex);  // 로그 예외 기록 - 공백 검색 시
        return new ResponseEntity<>(List.of(), HttpStatus.OK);
    }
}
