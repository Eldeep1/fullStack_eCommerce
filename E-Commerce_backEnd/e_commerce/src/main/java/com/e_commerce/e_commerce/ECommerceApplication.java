package com.e_commerce.e_commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({
	"com.e_commerce.e_commerce.services.auth.login",
 	"com.e_commerce.e_commerce.services.auth.register", 
	"com.e_commerce.e_commerce.services.products"
})

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
