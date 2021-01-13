package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedNativeQueries({
	@NamedNativeQuery(name = "product.findByProductName", query = "select p from Product p where p.productName=?"),
	@NamedNativeQuery(name = "product.findByProductType", query = "select p from Product p where p.productCategory=?"), })
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;

	private String productName;

	private float price;

	private int quantity;
	
	private String productCategory;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Cart cart;

	public Product() {
		super();
	}

	public Product(String productName, float price,String productCategory) {
		this.productName = productName;
		this.price = price;
		this.productCategory=productCategory;

	}

	public Product(int productId, String productName, float price, int quantity, String productCategory, Cart cart) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.productCategory = productCategory;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	

	

}
