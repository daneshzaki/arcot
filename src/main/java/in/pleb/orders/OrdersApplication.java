package in.pleb.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}

}
/**
 * 
 * TODO
 * check schema location - F:\code\graphql\orders\bin\main
 * add mutation
 * add multiple customer to order
 * change Map to nosql db
 * check with 2 queries with new code
  * class and method names are based on the GraphQL Spring Boot framework -- done
  * impl POST -- done
 */