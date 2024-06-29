package org.example.whereismyvacation.exception.exceptionhandler;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE)
public class AllExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
		log.error("",ex);
		return null;
	}
}
