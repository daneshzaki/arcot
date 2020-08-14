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
 * add query variables
 * 
 * try subscriptions
 * try/read fragments
 * try/read unions 
 * --
 * add mutation - code in all classes -- done
 * try PropertyDataFetcher -- done -- PropertyDataFetcher is used to map a different name to a property e.g. id instead of orderID 
 * add multiple items to order -- done
 * change Strings data to POJO Classes -- done
 * check schema location -- done -- when graphql schema under resources changes, it is gen to bin/main
 * check with 2 queries with new code -- done -- one Query tag but can have multiple queries inside
 * changing Spring Boot class and method names -- done -- class and method names are based on the GraphQL-Java Spring Boot framework so changing will break the flow
 * impl POST -- done
 */