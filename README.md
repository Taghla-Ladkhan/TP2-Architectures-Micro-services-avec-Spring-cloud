# TP2-Architectures-Micro-services-avec-Spring-cloud
## Partie 1 : (Customer-Service, Inventory-Service, Spring Cloud Gateway, Eureka Discovery)
### Customer Service : Gestion des clients
- Customer
```bash
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
```
- Application
```bash
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
```
![image](https://github.com/Taghla-Ladkhan/TP2-Architectures-Micro-services-avec-Spring-cloud/assets/101521160/f5d92dd4-4b1e-4603-ba81-7776dc12e0f7)

### Inventory Service : Gestion des produits
- Product
 ```bash
  @Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
```
- Application
```bash
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
```
![image](https://github.com/Taghla-Ladkhan/TP2-Architectures-Micro-services-avec-Spring-cloud/assets/101521160/a66c09b4-b67a-47a3-beb9-e5943d458467)

### Gateway Service : passerelle entre les microservices
- Application
```bash
@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
	@Bean
	public DiscoveryClientRouteDefinitionLocator definitionLocator(
			ReactiveDiscoveryClient rdc,
			DiscoveryLocatorProperties properties) {
		return new DiscoveryClientRouteDefinitionLocator(rdc, properties);
	}


}
```

- application.yml
```bash
  spring:
  cloud:
    gateway:
      routes:
        - id: r1
          uri: http://localhost:8081/
          predicates:
            - Path=/customers/**

        - id: r2
          uri: http://localhost:8082/
          predicates:
            - Path=/products/**

      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOrigins: "*"
            allowedHeaders: "*"
            allowedMethods:
              - GET
              - POST
              - PUT
              - DELETE
```
![image](https://github.com/Taghla-Ladkhan/TP2-Architectures-Micro-services-avec-Spring-cloud/assets/101521160/21db9c36-3d05-41c5-9c58-f8035e92f812)

### Eureka Discovery 
- application.properties
```bash
server.port=8761
eureka.client.fetch-registry=false
eureka.client.register-with-eureka=false
```
- Application
```bash
@Bean
@SpringBootApplication
@EnableEurekaServer
public class EurikaDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurikaDiscoveryApplication.class, args);
	}

}
```
![image](https://github.com/Taghla-Ladkhan/TP2-Architectures-Micro-services-avec-Spring-cloud/assets/101521160/3620c7c5-e00b-4abf-b35c-cf8a85fb4f4a)


## Partie 2 :  Billing Service avec Open Feign Rest Client
Feign dans cette application est utilisé pour deux opérations clés :
- Récupérer les informations du client à partir du service Customer
- Récupérer la liste des produits à partir du service ProductItem
  
![image](https://github.com/Taghla-Ladkhan/TP2-Architectures-Micro-services-avec-Spring-cloud/assets/101521160/c175ba70-e6ff-49d3-85d4-f3ec1c59d12f)

![image](https://github.com/Taghla-Ladkhan/TP2-Architectures-Micro-services-avec-Spring-cloud/assets/101521160/4ec44c97-84a2-45be-ab66-86228d9a0abd)


## Partie 3 : Angular
![image](https://github.com/Taghla-Ladkhan/TP2-Architectures-Micro-services-avec-Spring-cloud/assets/101521160/52aa37c6-de6b-4170-a2c3-1b8c039c0cdb)

![image](https://github.com/Taghla-Ladkhan/TP2-Architectures-Micro-services-avec-Spring-cloud/assets/101521160/c8b21498-1dda-4d62-b3de-ce3f0f54cf2c)

![image](https://github.com/Taghla-Ladkhan/TP2-Architectures-Micro-services-avec-Spring-cloud/assets/101521160/d6f1915e-84bf-4a1e-adcd-6f5c5acdfd13)






  



