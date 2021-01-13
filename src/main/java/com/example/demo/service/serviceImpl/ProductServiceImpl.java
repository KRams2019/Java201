package com.example.demo.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ApparalRepository;
import com.example.demo.dao.BookRepository;
import com.example.demo.dao.ProductRepository;
import com.example.demo.dto.ApparalDto;
import com.example.demo.dto.BookDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Apparal;
import com.example.demo.entity.Book;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	public final static Logger log = Logger.getLogger(ProductServiceImpl.class.getName());
	
	@Autowired
	BookRepository bookRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ApparalRepository apparalRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	public BookDto addProduct(BookDto bookDto) {
		Book book = convertDtoToEntity(bookDto);
		Book savedBook = bookRepository.save(book);
		BookDto returnBookDto = convertEntityToDto(savedBook);
		log.info("Product of type Book Addded Successfully");
		return returnBookDto;
	}

	@Override
	public ApparalDto addProductApparal(ApparalDto apparalDto) {
		Apparal apparal = convertDtoToEntity(apparalDto);
		Apparal savedApparal = apparalRepository.save(apparal);
		log.info("Product of type Apparal Addded Successfully");
		return convertEntityToDto(savedApparal);
	}

	@Override
	public ProductDto getProductByName(String productName) throws ServiceException {

		Product product = productRepository.findByProductName(productName);
		try {
			if (product == null) {
				log.error("Product Not Found With Entered Product Name");
				throw new ProductNotFoundException("Product Not Found with Entered Product Name");
				
			} else
				log.info("Product Returned successfully with productName "+productName);
				return convertEntityToDto(product);
		} catch (ProductNotFoundException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<ProductDto> getProductByCategory(String productCategory) throws ServiceException {
		List<Product> products = productRepository.findByProductCategory(productCategory);
		try {
			if (products.size() == 0) {
				log.error("Product Not Found With Entered ProductType");
				throw new ProductNotFoundException("Product Not Found with given ProductType");
			} else {
				log.info("Product Returned successfully with Product Type "+productCategory);
				List<ProductDto> productDtoList = products.stream().map(entity -> convertEntityToDto(entity))
						.collect(Collectors.toList());
				return productDtoList;
			}

		} catch (ProductNotFoundException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public BookDto convertEntityToDto(Book book) {
		return modelMapper.map(book, BookDto.class);

	}

	public Book convertDtoToEntity(BookDto bookDto) {
		return modelMapper.map(bookDto, Book.class);
	}

	public ApparalDto convertEntityToDto(Apparal apparal) {
		return modelMapper.map(apparal, ApparalDto.class);

	}

	public Apparal convertDtoToEntity(ApparalDto apparalDto) {
		return modelMapper.map(apparalDto, Apparal.class);
	}

	public ProductDto convertEntityToDto(Product product) {
		return modelMapper.map(product, ProductDto.class);

	}

	public Product convertDtoToEntity(ProductDto productDto) {
		return modelMapper.map(productDto, Product.class);
	}

}
