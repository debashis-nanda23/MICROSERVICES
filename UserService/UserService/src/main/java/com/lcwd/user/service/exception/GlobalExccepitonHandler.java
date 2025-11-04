package com.lcwd.user.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lcwd.user.service.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExccepitonHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> hanldeResourceNotFoundException(ResourceNotFoundException rnfe){
		String message=rnfe.getMessage();
		ApiResponse response= ApiResponse.builder().message(message).sucess(true).status(HttpStatus.NOT_FOUND).build();
	   return  new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	 }

}
