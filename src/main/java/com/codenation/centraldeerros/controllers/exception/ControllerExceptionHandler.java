package com.codenation.centraldeerros.controllers.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandardError> comumException(Exception e, HttpServletRequest request) {

		StandardError err = StandardError.builder().status(HttpStatus.UNAUTHORIZED.value()).message(e.getMessage()).build();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
}
