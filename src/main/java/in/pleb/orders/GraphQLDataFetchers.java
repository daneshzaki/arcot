package in.pleb.orders;

import in.pleb.orders.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;

import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class GraphQLDataFetchers {

        /*
        private static List<Map<String, String>> orders = Arrays.asList(
                        ImmutableMap.of("id", "o1", "date", "07/30/2020", "amount", "23.95", "customerId", "c1",
                                        "itemId", "i1"),
                        ImmutableMap.of("id", "o2", "date", "07/25/2020", "amount", "230.95", "customerId", "c2",
                                        "itemId", "i2"));
        

        private static List<Map<String, String>> customers = Arrays.asList(
                        ImmutableMap.of("id", "c1", "firstName", "Taabish", "lastName", "Zaki", "phone", "253-098-2211",
                                        "address", "Seattle WA"),
                        ImmutableMap.of("id", "c2", "firstName", "Karim", "lastName", "Ali", "phone", "403-865-1344",
                                        "address", "Chicago IL"));

        private static List<Map<String, String>> items = Arrays.asList(
                        ImmutableMap.of("id", "i1", "type", "Book", "price", "23.95", "description", "Moby Dick"),
                        ImmutableMap.of("id", "i2", "type", "Mouse", "price", "8.95", "description",
                                        "Logitech Wireless Mouse WM980"));
         public DataFetcher getOrderByIdDataFetcher() {

                return dataFetchingEnvironment -> {
                        String orderId = dataFetchingEnvironment.getArgument("id");
                        System.out.println("GraphQLdataFetchers get order for " + orderId);
                        return orders.stream().filter(order -> order.get("id").equals(orderId)).findFirst()
                                        .orElse(null);
                };
        }

        public DataFetcher getCustomerByIdDataFetcher() {

                return dataFetchingEnvironment -> {
                        String customerId = dataFetchingEnvironment.getArgument("id");
                        System.out.println("GraphQLdataFetchers get customer for " + customerId);
                        return customers.stream().filter(order -> order.get("id").equals(customerId)).findFirst()
                                        .orElse(null);
                };
        }

        public DataFetcher getCustomerDataFetcher() {
                return dataFetchingEnvironment -> {
                        Map<String, String> order = dataFetchingEnvironment.getSource();
                        String customerId = order.get("customerId");
                        System.out.println("GraphQLdataFetchers get customer for " + customerId);
                        return customers.stream().filter(item -> item.get("id").equals(customerId)).findFirst()
                                        .orElse(null);
                };
        }

        public DataFetcher getItemDataFetcher() {
                return dataFetchingEnvironment -> {
                        Map<String, String> order = dataFetchingEnvironment.getSource();
                        String itemId = order.get("itemId");
                        System.out.println("GraphQLdataFetchers get item for " + itemId);
                        return items.stream().filter(item -> item.get("id").equals(itemId)).findFirst().orElse(null);
                };
        }

                                        */
        
        private static List<Order> orders = new ArrayList<Order>(
                List.of(
                        new Order("o1", "03-07-20", "23.95", "c1", "created", new ArrayList<Item> (List.of(new Item("i3", "vessels", "Porcelain mug PM998", "9.95")))),
                        new Order("o2", "04-07-20", "10.95", "c2", "shipped", new ArrayList<Item> (List.of(new Item("i4", "book", "Harry Potter and Sorcerers Stone", "23.95"))))));
                        

        private static List<Customer> customers = new ArrayList<Customer>(
                List.of(new Customer("c1", "dan", "harkey", "253-912-9627", "Seattle WA"), new Customer("c2", "bill", "blert", "501-322-1945", "Chicago IL"))
        );
        private static List<Item> items = new ArrayList<Item>(
                List.of(new Item("i1", "electronics", "Samsung Galaxy Headset sw123", "102.95"), new Item("i2", "furniture", "Swivel chair Swelte 13WQ", "350.95"))
        );

        public DataFetcher getOrderByIdDataFetcher() {

                return dataFetchingEnvironment -> {
                        String orderId = dataFetchingEnvironment.getArgument("id");
                        System.out.println("GraphQLdataFetchers get order for " + orderId);
                        return orders.stream().filter(order -> order.getId().equals(orderId)).findFirst()
                                        .orElse(null);
                };
        }

        public DataFetcher getCustomerDataFetcher() {
                return dataFetchingEnvironment -> {
                        Order order = dataFetchingEnvironment.getSource();
                        String customerId = order.getCustomerId();
                        System.out.println("GraphQLdataFetchers get customer for " + customerId);
                        return customers.stream().filter(customer -> customer.getId().equals(customerId)).findFirst()
                                        .orElse(null);
                };
        }

        public DataFetcher getItemDataFetcher() {
                return dataFetchingEnvironment -> {
                        Order order = dataFetchingEnvironment.getSource();
                        //String itemId = order.getItems();
                        //System.out.println("GraphQLdataFetchers get item for " + itemId);
                        //System.out.println("GraphQLdataFetchers fetched " + items.stream().filter(item -> item.getId().equals(itemId)).collect(Collectors.toList()));
                        //return items.stream().filter(item -> item.getId().equals(itemId)).findFirst().orElse(null);
                        //return items.stream().filter(item -> item.getId().equals(itemId)).collect(Collectors.toList());
                        System.out.println("GraphQLDataFetchers order items = "+order.getItems().toString());
                        return order.getItems();
                        /**
                         * TODO: 
                         * add code to add item with id only in Order
                         * add code above to read id and get corresponding item
                         */
                };
        }

        //TODO: add mutation datafetcher method
        public DataFetcher createOrderDataFetcher()
        {
                System.out.println("GraphQLDataFetchers create order ");
                return dataFetchingEnvironment -> {
                        System.out.println("GraphQLDataFetchers create order dataFetchingEnvironment is "+dataFetchingEnvironment.getArguments().toString());
                        LinkedHashMap  ordering = dataFetchingEnvironment.getArgument("ordering");
                        System.out.println("GraphQLDataFetchers create order ordering = "+ordering.toString());
                        System.out.println("GraphQLDataFetchers  create order order id = "+ordering.get("id"));
                        String orderId = (String) ordering.get("id");
                        System.out.println("GraphQLDataFetchers create order id = "+orderId);
                        String customerId = (String) ordering.get("customerID");
                        String orderDate = (String) ordering.get("date");
                        String orderAmount = (String) ordering.get("amount");
                        String orderStatus = (String) ordering.get("status");
                        //TODO impl Item obj
                        List orderingItems = new ArrayList(List.of(ordering.get("item")));
                        
                        orders.add(new Order(orderId, orderDate, orderAmount, customerId, orderStatus, orderingItems));

                        return orders;

                        

                };
        }

        public DataFetcher updateOrderDataFetcher()
        {
                System.out.println("GraphQLDataFetchers update order ");
                //TODO: add code to update fields for order id and change return type
                return null;
        }

        public DataFetcher deleteOrderDataFetcher()
        {
                System.out.println("GraphQLDataFetchers delete order ");
                //TODO: add code to delete  fields for order id and change return type
                return null;
        }

}