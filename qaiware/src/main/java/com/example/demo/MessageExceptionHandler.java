package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MessageExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<MessageEmpty> handleException(MessageErrorException messageEx) {
		MessageEmpty messageError = new MessageEmpty();

		return new ResponseEntity<>(messageError, HttpStatus.PRECONDITION_FAILED);	
	}
	
	@ExceptionHandler
	public ResponseEntity<MessageEmpty> handleException(Exception ex) {
		MessageEmpty messageError = new MessageEmpty();

		return new ResponseEntity<>(messageError, HttpStatus.BAD_REQUEST);	
	}
}
