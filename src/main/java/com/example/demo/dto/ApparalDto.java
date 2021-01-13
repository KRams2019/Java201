package com.example.demo.dto;

public class ApparalDto {

	private String type;
	private String band;
	private String design;
	private float price;
	private String productName;
	private String productCategory;

	public ApparalDto() {
		super();
	}

	public ApparalDto(String type, String band, String design, float price, String productName,
			String productCategory) {
		super();
		this.type = type;
		this.band = band;
		this.design = design;
		this.price = price;
		this.productName = productName;
		this.productCategory = productCategory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

}
