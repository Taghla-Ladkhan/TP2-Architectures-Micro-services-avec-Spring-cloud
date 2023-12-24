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
## Partie 2 :  Billing Service avec Open Feign Rest Client
Feign dans cette application est utilisé pour deux opérations clés :
- Récupérer les informations du client à partir du service Customer
- Récupérer la liste des produits à partir du service ProductItem

## Partie 3 : Angular

  



