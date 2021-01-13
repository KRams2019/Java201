package com.example.demo;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ShoppingCartApplication {
	
	private static Logger logger = LogManager.getRootLogger();


	public static void main(String[] args) {

		PropertyConfigurator.configure("..\\shopping_cart_application\\src\\main\\resources\\log4j2.properties");
		logger.info("Starting the springboot app...");
		SpringApplication.run(ShoppingCartApplication.class, args);

	}

}
