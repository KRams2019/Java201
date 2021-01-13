package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CartDto {

	private int cartId;

	@JsonIgnoreProperties("cart")
	private List<ProductDto> products;

	private float totalPrice;

	@JsonIgnoreProperties("cart")
	private UserDto user;

	public CartDto() {
		super();
	}

	public CartDto(int cartId, List<ProductDto> products, float totalPrice, UserDto user) {
		super();
		this.cartId = cartId;
		this.products = products;
		this.totalPrice = totalPrice;
		this.user = user;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

}
