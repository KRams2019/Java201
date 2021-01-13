package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "productId")
public class Book extends Product {

	private String genere;
	private String authour;
	private String publication;


	public Book() {
		super();
	}

	public Book(String productName, float price,String productCategory, String genere, String authour, String publication) {
		super(productName, price,productCategory);
		this.genere = genere;
		this.authour = authour;
		this.publication = publication;
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

}
