package com.alticart.coding.exception;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandleExceptions extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Object> generateResponse(Exception ex) {
		System.out.println(ex.getClass() + " - " + ex.getMessage());
		Map map = new LinkedHashMap<>();
		map.put("responseCode", "500");

		map.put("responseMessage", ex.getMessage());
		return ResponseEntity.status(500).headers(new HttpHeaders()).body(map);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, String> errors = new LinkedHashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put("responseCode", "400");

			errors.put(fieldName, errorMessage);

		});
		return ResponseEntity.status(400).headers(new HttpHeaders()).body(errors);
	}

}
