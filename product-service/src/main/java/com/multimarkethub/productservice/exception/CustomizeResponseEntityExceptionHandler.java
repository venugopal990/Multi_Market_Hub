package com.multimarkethub.productservice.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.multimarkethub.productservice.beans.Response;



@ControllerAdvice
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Response> handleAllException(Exception ex, WebRequest request) throws Exception {
		ex.printStackTrace();
		return new ResponseEntity<>(new Response(LocalDateTime.now(),false, ex.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Response> handleNotFoundException(Exception ex, WebRequest request) throws Exception {
		
		return new ResponseEntity<>(new Response(LocalDateTime.now(),false, ex.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
		
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String errors = ex.getFieldErrors().stream().map(n -> String.valueOf("Field:"+n.getField()+"-"+n.getDefaultMessage())).collect(Collectors.joining(",","{","}"));
		int errorCount = ex.getFieldErrorCount();
		return new ResponseEntity<>(new Response(LocalDateTime.now(),false, "Total error count: "+errorCount+". Errors are:"+ errors , request.getDescription(false)), HttpStatus.BAD_REQUEST);
	}

}
