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
 * add mutation - code in all classes
 * add query variables
 * try PropertyDataFetcher
 * try subscriptions
 * try fragments
 * try unions 
 * change Map to nosql db
 * add multiple items to order -- done
 * change Strings data to POJO Classes -- done
 * check schema location -- done -- when graphql schema under resources changes, it is gen to bin/main
 * check with 2 queries with new code -- done -- one Query tag but can have multiple queries inside
 * changing Spring Boot class and method names -- done -- class and method names are based on the GraphQL-Java Spring Boot framework so changing will break the flow
 * impl POST -- done
 */