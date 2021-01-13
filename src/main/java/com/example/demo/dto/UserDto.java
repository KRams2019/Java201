package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class UserDto {

	
	private String userName;
	private String address;

	@JsonIgnoreProperties("user")
	private CartDto cart;

	public UserDto() {
		super();
	}

	public UserDto(String userName, String address, CartDto cart) {
		super();
		
		this.userName = userName;
		this.address = address;
		this.cart = cart;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CartDto getCart() {
		return cart;
	}

	public void setCart(CartDto cart) {
		this.cart = cart;
	}

}
