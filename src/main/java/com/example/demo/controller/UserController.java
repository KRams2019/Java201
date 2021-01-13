package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.apiResponse.ApiResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/add-user")
	public ResponseEntity<ApiResponse> addUser(@RequestBody UserDto userDto) throws ServiceException {
		UserDto SavedUserDto = userService.addUser(userDto);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Adding Users. ", false, SavedUserDto, HttpStatus.OK),
				HttpStatus.OK);

	}

}
