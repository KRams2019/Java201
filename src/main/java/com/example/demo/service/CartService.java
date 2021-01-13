package com.example.demo.service;

import com.example.demo.dto.CartDto;
import com.example.demo.exception.ServiceException;

public interface CartService {

	public CartDto addProductIntoCart(int userId, int productId) throws ServiceException;

	public CartDto removeProductFromCart(int cartId, int productId) throws ServiceException;

	public CartDto removeAllProductFromCart(int cartId) throws ServiceException;

	public CartDto viewCart(int cartId) throws ServiceException;
	
	public String updateCart(int cartId,int productId,int quantity) throws ServiceException;

}
