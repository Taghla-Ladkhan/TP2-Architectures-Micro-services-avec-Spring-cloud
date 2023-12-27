package com.example.customerservice;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration) {
		restConfiguration.exposeIdsFor(Customer.class);
		return args -> {
			customerRepository.saveAll(List.of(
					Customer.builder().name("tarhla").email("ladkhan@gmail.com").build(),
					Customer.builder().name("oussama").email("ahmed@gmail.com").build(),
					Customer.builder().name("imane").email("imane@gmail.com").build()
			));
			customerRepository.findAll().forEach(System.out::println);
		};
	}
}
