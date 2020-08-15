package in.pleb.orders;

import in.pleb.orders.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;

import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * GraphQLDataFetchers retrieves values or updates the fields in the GraphQL schema.  
 * 
 */

@Component
public class GraphQLDataFetchers {
        
        //sample static data for orders
        private static List<Order> orders = new ArrayList<Order>(
                List.of(
                        new Order("o1", "03-07-20", "23.95", "c1", "created", new ArrayList<Item> (List.of(new Item("i3", "vessels", "Porcelain mug PM998", "9.95")))),
                        new Order("o2", "04-07-20", "10.95", "c2", "shipped", new ArrayList<Item> (List.of(new Item("i4", "book", "Harry Potter and Sorcerers Stone", "23.95"))))));
                        
        //sample static data for customers
        private static List<Customer> customers = new ArrayList<Customer>(
                List.of(new Customer("c1", "dan", "harkey", "253-912-9627", "Seattle WA"), new Customer("c2", "bill", "blert", "501-322-1945", "Chicago IL"))
        );

        //sample static data for items
        private static List<Item> items = new ArrayList<Item>(
                List.of(new Item("i1", "electronics", "Samsung Galaxy Headset sw123", "102.95"), new Item("i2", "furniture", "Swivel chair Swelte 13WQ", "350.95"))
        );

        //method to handle order query
        public DataFetcher getOrderByIdDataFetcher() {

                return dataFetchingEnvironment -> {
                        String orderId = dataFetchingEnvironment.getArgument("id");
                        System.out.println("GraphQLdataFetchers get order for " + orderId);
                        return orders.stream().filter(order -> order.getId().equals(orderId)).findFirst()
                                        .orElse(null);
                };
        }

        //method to fetch customer data part of the order query
        public DataFetcher getCustomerDataFetcher() {
                return dataFetchingEnvironment -> {
                        Order order = dataFetchingEnvironment.getSource();
                        String customerId = order.getCustomerId().trim();
                        System.out.println("GraphQLdataFetchers get customer for " + customerId);
                        for (Customer customer : customers) {
                                if (customer.getId().equals(customerId)) {
                                    System.out.println("GraphQLdataFetchers customer match is " + customer.toString());            
                                    return customer;                                    
                                }
                        }
                        System.out.println("GraphQLdataFetchers no match returning null");
                        return null;
                };
        }

        //method to fetch item data part of the order query
        public DataFetcher getItemDataFetcher() {
                return dataFetchingEnvironment -> {
                        Order order = dataFetchingEnvironment.getSource();

                        //get all items
                        List orderItemsList = ((List) ((List) order.getItems()).get(0));
                        System.out.println("GraphQLDataFetchers orderItems = "+orderItemsList.toString());
                        
                        //build the items
                        String[] orderItemValues;
                        LinkedHashMap orderItemsMap;
                        Item orderItem;
                        //list to hold the final values
                        List populatedOrderItemList = new ArrayList();

                        //loop and get the values from each obj in map
                        Object[] orderItemsArr = orderItemsList.toArray();
                        for(Object obj : orderItemsArr)
                        {
                                System.out.println("GraphQLDataFetchers building items obj = "+obj.toString());
                                orderItemsMap = (LinkedHashMap) obj;
                                System.out.println("GraphQLDataFetchers building items item from obj = "+orderItemsMap.values());
                                orderItemValues = Arrays.copyOf(orderItemsMap.values().toArray(), orderItemsMap.values().toArray().length, String[].class);
                                orderItem = new Item(orderItemValues[0], orderItemValues[1], orderItemValues[3], orderItemValues[2]);
                                System.out.println("GraphQLDataFetchers builder = "+orderItem.toString());
                                populatedOrderItemList.add(orderItem);                                
                        }

                        return populatedOrderItemList;

                };
        }

        //method to insert orders - adds to order list
        public DataFetcher createOrderDataFetcher()
        {
                System.out.println("GraphQLDataFetchers create order ");
                return dataFetchingEnvironment -> {
                        System.out.println("GraphQLDataFetchers create order dataFetchingEnvironment is "+dataFetchingEnvironment.getArguments().toString());
                        
                        //get the order details input
                        LinkedHashMap  ordering = dataFetchingEnvironment.getArgument("ordering");
                        System.out.println("GraphQLDataFetchers create order ordering = "+ordering.toString());
                        System.out.println("GraphQLDataFetchers  create order order id = "+ordering.get("id"));                        
                        String orderId = (String) ordering.get("id");
                        System.out.println("GraphQLDataFetchers create order id = "+orderId);
                        String customerId = (String) ordering.get("customerID");
                        String orderDate = (String) ordering.get("date");
                        String orderAmount = (String) ordering.get("amount");
                        String orderStatus = (String) ordering.get("status");

                        //order POJO contains items as a list so creating one before adding
                        List orderingItems = new ArrayList(List.of(ordering.get("item")));                       
                        orders.add(new Order(orderId, orderDate, orderAmount, customerId, orderStatus, orderingItems));

                        return orders;
                };
        }
}