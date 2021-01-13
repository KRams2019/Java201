package com.example.demo.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BookRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dto.CartDto;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.exception.CartIdNotFoundException;
import com.example.demo.exception.ProductAlreadyExistsException;
import com.example.demo.exception.ProductIdNotFoundException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.QuantityNotNegativeException;
import com.example.demo.exception.ServiceException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.CartService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CartServiceImpl implements CartService {
	
	public final static Logger log = Logger.getLogger(CartServiceImpl.class.getName());
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	BookRepository bookRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public CartDto addProductIntoCart(int userId, int productId) throws ServiceException {

		User user = userRepository.getOne(userId);
		Product product = productRepository.getOne(productId);
		List<Product> products = new ArrayList<Product>();
		try {

			userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Id Not Found"));
			productRepository.findById(productId)
					.orElseThrow(() -> new ProductIdNotFoundException("Product Id Not Found"));

			if (product.getCart() == null || (product.getCart().getUser().getUserId() == userId)) {
				products.add(product);
				Cart cart;
				if (user.getCart() == null) {

					cart = new Cart();
					cart.setProducts(products);
					cart.setUser(user);
				} else {
					cart = user.getCart();
					cart.setProducts(products);
					cart.setUser(user);
				}
				Cart savedCart = cartRepository.save(cart);
				product.setCart(cart);
				product.setQuantity(product.getQuantity() + 1);
				productRepository.save(product);
				CartDto cartDto = convertEntityToDto(savedCart);
				log.info("Product Added Successfully into cart with cart Id: "+cart.getCartId()+" "+ "and UserId: "+cart.getUser().getUserId());
				return cartDto;

			} else {
				log.error("Product Cannot Be Added into cart as product Already Taken");
				throw new ProductAlreadyExistsException("Product Already Taken");
			}

		} catch (ProductAlreadyExistsException pe) {

			throw new ServiceException(pe.getMessage(), pe);
		}

		catch (UserNotFoundException ue) {

			throw new ServiceException(ue.getMessage(), ue);
		}

		catch (ProductIdNotFoundException p) {

			throw new ServiceException(p.getMessage(), p);
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public CartDto removeProductFromCart(int cartId, int productId) throws ServiceException {

		try {
			cartRepository.findById(cartId).orElseThrow(() -> new CartIdNotFoundException("Cart Id Not Found"));
			productRepository.findById(productId)
					.orElseThrow(() -> new ProductIdNotFoundException("Product Id Not Found"));

			Cart cart = cartRepository.getOne(cartId);
			Product product = productRepository.getOne(productId);

			List<Product> products = cart.getProducts();
			if(products.removeIf(e -> e.getProductId() == productId)) {
				cart.setProducts(products);
				product.setQuantity(0);
				product.setCart(null);
				productRepository.save(product);
				Cart savedCart = cartRepository.save(cart);
				log.info("Product Removed Successfully into cart with cart Id: "+cart.getCartId()+" "+ "and Product Id: "+productId);
				CartDto cartDto = convertEntityToDto(savedCart);
				return cartDto;
			}
			else {
				log.error("Product Not Found in Cart");
				throw new ProductIdNotFoundException("Product Not Found in Cart");
			}
		} catch (CartIdNotFoundException ce) {

			throw new ServiceException(ce.getMessage(), ce);

		}

		catch (ProductIdNotFoundException pe) {
			throw new ServiceException(pe.getMessage(), pe);
		}
	
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public CartDto removeAllProductFromCart(int cartId) throws ServiceException {

		try {
			cartRepository.findById(cartId).orElseThrow(() -> new CartIdNotFoundException("Cart Id Not Found"));
			Cart cart = cartRepository.getOne(cartId);
			for (Product product : cart.getProducts()) {
				product.setCart(null);
				product.setQuantity(0);
				productRepository.save(product);
			}
			cart.getProducts().clear();
			Cart savedRepository = cartRepository.save(cart);
			log.info(" All Product Successfully removed from cart with cart Id: "+cartId);
			CartDto cartDto = convertEntityToDto(savedRepository);
			return cartDto;

		} catch (CartIdNotFoundException ce) {
			log.error(" Error in Removing Products As Cart Id not found: "+cartId);
			throw new ServiceException(ce.getMessage(), ce);
		}

	}

	@Override
	public CartDto viewCart(int cartId) throws ServiceException {

		try {
			cartRepository.findById(cartId).orElseThrow(() -> new CartIdNotFoundException("Cart Id Not Found"));
			Cart cart = cartRepository.getOne(cartId);
			float price = 0;
			for (Product product : cart.getProducts()) {
				System.out.println(product.getPrice());
				price += product.getPrice() * product.getQuantity();

			}
			cart.setTotalPrice(price);
			Cart savedCart = cartRepository.save(cart);
			CartDto cartDto = convertEntityToDto(savedCart);
			log.info("View Cart with cartId: "+cartId+" "+"and Total Price Rs."+price);
			return cartDto;
		} catch (CartIdNotFoundException ce) {

			throw new ServiceException(ce.getMessage(), ce);
		}
	}

	@Override
	public String updateCart(int cartId, int productId, int quantity) throws ServiceException {

		try {
			cartRepository.findById(cartId).orElseThrow(() -> new CartIdNotFoundException("Cart Id Not Found"));
			productRepository.findById(productId)
					.orElseThrow(() -> new ProductIdNotFoundException("Product Id Not Found"));
			Cart cart = cartRepository.getOne(cartId);
			if (quantity > 0) {
				int cnt = 0;
				for (Product product : cart.getProducts()) {
					if (product.getProductId() == productId) {
						cnt++;
						product.setQuantity(quantity);
						productRepository.save(product);
						log.info("Product Quantity Successfully Updated  with cart Id: "+cartId);
						return "Successfully Updated Product in Cart with quantity " + quantity;

					}

				}

				if (cnt == 0) {		
					log.info("Product Quantity Cannot Updated as Product Not Found");
					throw new ProductNotFoundException("Product Not Found In Cart");
					
				}

			} else if (quantity == 0) {
				removeAllProductFromCart(cartId);
				log.info("Products Successfully Removed  with cart Id: "+cartId+""+"as Quantity 0");
				return "Removed All Product As Quantity is " + quantity;
			}

			else {
				log.error("Product Quantity Cannot be negative");
				throw new QuantityNotNegativeException("Quantity Not Be Negative");
			}

		} catch (CartIdNotFoundException e) {
			throw new ServiceException(e.getMessage(), e);
		} catch (QuantityNotNegativeException qe) {
			throw new ServiceException(qe.getMessage(), qe);
		}
		return null;

	}

	public CartDto convertEntityToDto(Cart cart) {
		return modelMapper.map(cart, CartDto.class);

	}

	public Cart convertDtoToEntity(CartDto cartDto) {
		return modelMapper.map(cartDto, Cart.class);
	}

}
