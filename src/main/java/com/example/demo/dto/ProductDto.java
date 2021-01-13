package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ProductDto {

	private int productId;

	private String productName;

	private float price;

	private String productCategory;

	private int quantity;

	@JsonIgnoreProperties("products")
	private CartDto cart;

	public ProductDto() {
		super();
	}

	public ProductDto(int productId, String productName, float price, String productCategory, int quantity,
			CartDto cart) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.productCategory = productCategory;
		this.quantity = quantity;
		this.cart = cart;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartDto getCart() {
		return cart;
	}

	public void setCart(CartDto cart) {
		this.cart = cart;
	}

}
