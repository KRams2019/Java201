package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ApparalDto;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.exception.ServiceException;

public interface ProductService {

	public BookDto addProduct(BookDto bookDto);

	public ApparalDto addProductApparal(ApparalDto apparalDto);
	
	
	public ProductDto getProductByName(String productName) throws ServiceException;
	
	public List<ProductDto> getProductByCategory(String productCategory) throws ServiceException;

}
