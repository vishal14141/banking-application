package com.bank.sample.app.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandling {
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> badRequestException(WebRequest request){
		String exceptionMessage = "Your request can not be procced/ Invaid request";
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exceptionMessage, request.getDescription(false), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(WebRequest request){
		String exceptionMessage = "Requested Resource is not AVailable";
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exceptionMessage, request.getDescription(false), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerAlreadyExist.class)
	public ResponseEntity<?> customerAlreadyExist(WebRequest request){
		String exceptionMessage = "Resource already exist";
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exceptionMessage, request.getDescription(false), HttpStatus.CONFLICT);
		return new ResponseEntity<>(errorDetails,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> fieldValidatonException(WebRequest request){
		String exceptionMessage = "Validation failed for arguments";
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exceptionMessage, request.getDescription(false), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}

}
