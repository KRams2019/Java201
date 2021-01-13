package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "productId")
public class Apparal extends Product {

	private String type;
	private String band;
	private String design;

	public Apparal() {
		super();
	}

	public Apparal(String productName, float price,String productCategory, String type, String band, String design) {
		super(productName, price,productCategory);
		this.type = type;
		this.band = band;
		this.design = design;
	}

	public Apparal(String type, String band, String design) {
		this.type = type;
		this.band = band;
		this.design = design;
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

}
