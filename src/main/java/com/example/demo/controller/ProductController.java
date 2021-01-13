package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.apiResponse.ApiResponse;
import com.example.demo.dto.ApparalDto;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/addproduct/book")
	public ResponseEntity<ApiResponse> addProduct(@RequestBody BookDto bookDto) {
		BookDto SavedbookDto = productService.addProduct(bookDto);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Adding Book Product", false, SavedbookDto, HttpStatus.OK), HttpStatus.OK);

	}

	@PostMapping("/addproduct/apparal")
	public ResponseEntity<ApiResponse> addProductApparal(@RequestBody ApparalDto apparalDto) {
		ApparalDto SavedApparalDto = productService.addProductApparal(apparalDto);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Adding Apparal Product", false, SavedApparalDto, HttpStatus.OK), HttpStatus.OK);

	}

	@GetMapping("/getproductbyname/{productName}")
	public ResponseEntity<ApiResponse> getProductByName(@PathVariable String productName) throws ServiceException {
		ProductDto productDto = productService.getProductByName(productName);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Get Product By Product Name", false, productDto, HttpStatus.OK), HttpStatus.OK);
	}

	@GetMapping("/getproductbytype/{productCategory}")
	public ResponseEntity<ApiResponse> getProductByType(@PathVariable String productCategory) throws ServiceException {
		List<ProductDto> productDto = productService.getProductByCategory(productCategory);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Get Product By Product Name", false, productDto, HttpStatus.OK), HttpStatus.OK);
	}

}
