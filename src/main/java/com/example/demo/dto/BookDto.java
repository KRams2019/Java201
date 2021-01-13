package com.example.demo.dto;

public class BookDto {

	private String genere;
	private String authour;
	private String publication;
	private float price;
	private String productCategory;
	private String productName;

	public BookDto() {
		super();
	}

	public BookDto(String genere, String authour, String publication, float price, String productCategory,
			String productName) {
		super();
		this.genere = genere;
		this.authour = authour;
		this.publication = publication;
		this.price = price;
		this.productCategory = productCategory;
		this.productName = productName;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getAuthour() {
		return authour;
	}

	public void setAuthour(String authour) {
		this.authour = authour;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
