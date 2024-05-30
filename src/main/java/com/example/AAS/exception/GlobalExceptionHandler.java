package com.example.AAS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value =IdNotFoundException.class)
	public ResponseEntity<Object> idException(IdNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value =BadRequestException.class)
	public ResponseEntity<Object> badRequestException(BadRequestException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

	}
	
}
	