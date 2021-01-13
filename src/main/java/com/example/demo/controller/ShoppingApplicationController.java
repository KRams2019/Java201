package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.apiResponse.ApiResponse;
import com.example.demo.dto.CartDto;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/shopping")
public class ShoppingApplicationController {

	@Autowired
	CartService cartService;

	@PostMapping("/addproduct/{userId}/{productId}")
	public ResponseEntity<ApiResponse> addProductsIntoCart(@PathVariable int userId, @PathVariable int productId)
			throws ServiceException {
		CartDto cartDto = cartService.addProductIntoCart(userId, productId);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Adding Product Into Cart.", false, cartDto, HttpStatus.OK), HttpStatus.OK);

	}

	@PutMapping("/removeproduct/{cartId}/{productId}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int cartId, @PathVariable int productId)
			throws ServiceException {

		CartDto cartDto = cartService.removeProductFromCart(cartId, productId);

		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Removing Product from Cart.", false, cartDto, HttpStatus.OK), HttpStatus.OK);
	}

	@PutMapping("/removeAllProduct/{cartId}")
	public ResponseEntity<ApiResponse> deleteAllProduct(@PathVariable int cartId) throws ServiceException {

		CartDto cartDto = cartService.removeAllProductFromCart(cartId);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Removing All products from Cart.", false, cartDto, HttpStatus.OK), HttpStatus.OK);

	}

	@GetMapping("/viewcart/{cartId}")
	public ResponseEntity<ApiResponse> viewCart(@PathVariable int cartId) throws ServiceException {
		CartDto cartDto = cartService.viewCart(cartId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Viewing Cart.", false, cartDto, HttpStatus.OK),
				HttpStatus.OK);

	}

	@PutMapping("/updatequantity/{cartId}/{productId}/{quantity}")
	public ResponseEntity<ApiResponse> updateQuantity(@PathVariable int cartId, @PathVariable int productId,
			@PathVariable int quantity) throws ServiceException {
		String response = cartService.updateCart(cartId, productId, quantity);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Update Quantity in Cart. ", false, response, HttpStatus.OK), HttpStatus.OK);
	}

}
