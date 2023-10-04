package com.example.demo.services.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{

	//Method Argument Not Valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		CustomErrorDetails customeErrorDetails = 
				new CustomErrorDetails(new Date(), "From MethodArgumentNotValid in GEH", ex.getMessage());
	return new ResponseEntity<>(customeErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		CustomErrorDetails customeErrorDetails = 
				new CustomErrorDetails(new Date(), "From RequestMethodNotSupported in GEH - Method Not Allowed", ex.getMessage());
		return new ResponseEntity<>(customeErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	//username not found
	@ExceptionHandler({UserNameNotFoundException.class, OrderNotFoundException.class})
	public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request){
			CustomErrorDetails customeErrorDetails = 
					new CustomErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(customeErrorDetails, HttpStatus.NOT_FOUND);
	}
	
	//ConstraintViolationExceotion
	@ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException (ConstraintViolationException ex,
					WebRequest request){
		CustomErrorDetails customeErrorDetails = 
						new CustomErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(customeErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
}
