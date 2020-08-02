package in.pleb.orders;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class GraphQLDataFetchers {

    private static List<Map<String, String>> orders = Arrays.asList(
            ImmutableMap.of("id", "o1",
                    "date", "07/30/2020",    
                    "amount", "23.95",
                    "customerId", "c1",
                    "itemId", "i1"),
                    ImmutableMap.of("id", "o2",
                    "date", "07/25/2020",    
                    "amount", "230.95",
                    "customerId", "c2",
                    "itemId", "i2")
        );


    private static List<Map<String, String>> customers = Arrays.asList(
            ImmutableMap.of("id", "c1",
                    "firstName", "Taabish",
                    "lastName", "Zaki",
                    "phone","253-098-2211",
                    "address", "Seattle WA"),
              ImmutableMap.of("id", "c2",
              "firstName", "Karim",
              "lastName", "Ali",
              "phone","403-865-1344",
              "address", "Chicago IL")
        );


    private static List<Map<String, String>> items = Arrays.asList(
            ImmutableMap.of("id", "i1",
                    "type", "Book",
                    "price", "23.95",
                    "description","Moby Dick"),
              ImmutableMap.of("id", "i2",
                    "type", "Mouse",
                    "price", "8.95",
                    "description","Logitech Wireless Mouse WM980")                                        
        );

    public DataFetcher getOrderByIdDataFetcher() {
        
        return dataFetchingEnvironment -> {
            String orderId = dataFetchingEnvironment.getArgument("id");
            System.out.println("GraphQLdataFetchers get order for "+orderId);
            return orders
                    .stream()
                    .filter(order -> order.get("id").equals(orderId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getCustomerDataFetcher() {
        return dataFetchingEnvironment -> {
                Map<String,String> order = dataFetchingEnvironment.getSource();
                String customerId = order.get("customerId");
                System.out.println("GraphQLdataFetchers get customer for "+customerId);
                return customers
                        .stream()
                        .filter(item -> item.get("id").equals(customerId))
                        .findFirst()
                        .orElse(null);
            };
    }


    public DataFetcher getItemDataFetcher() {
        return dataFetchingEnvironment -> {
                Map<String,String> order = dataFetchingEnvironment.getSource();
                String itemId = order.get("itemId");
                System.out.println("GraphQLdataFetchers get item for "+itemId);
                return items
                        .stream()
                        .filter(item -> item.get("id").equals(itemId))
                        .findFirst()
                        .orElse(null);
            };
    }


}