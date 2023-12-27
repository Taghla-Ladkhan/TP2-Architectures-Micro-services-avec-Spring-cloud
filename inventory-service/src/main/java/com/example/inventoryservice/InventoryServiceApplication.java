package com.example.inventoryservice;

import com.example.inventoryservice.entities.Product;
import com.example.inventoryservice.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository,
							RepositoryRestConfiguration restConfiguration) {
		restConfiguration.exposeIdsFor(Product.class);
		return args -> {
				productRepository.save(new Product(null,"produit1",5000,3));
			productRepository.save(new Product(null,"produit2",6000,2));
			productRepository.save(new Product(null,"produit3",7000,9));

			productRepository.findAll().forEach(p->{
				System.out.println(p.getName());
			});

		};
	}
}
