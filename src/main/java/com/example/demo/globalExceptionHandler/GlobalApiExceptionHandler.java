package com.example.demo.globalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.apiResponse.ApiResponse;
import com.example.demo.controller.ProductController;
import com.example.demo.controller.ShoppingApplicationController;
import com.example.demo.controller.UserController;
import com.example.demo.exception.ServiceException;

@RestControllerAdvice(assignableTypes = { ShoppingApplicationController.class,UserController.class,ProductController.class })
public class GlobalApiExceptionHandler {
	    @ExceptionHandler(ServiceException.class)
	    public ResponseEntity<ApiResponse> ServiceExceptionhandler(Exception e, Throwable cause) {
	    	ApiResponse response = new ApiResponse("Exception Message", true, e.getMessage(), HttpStatus.BAD_REQUEST);
	        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	    }
}

