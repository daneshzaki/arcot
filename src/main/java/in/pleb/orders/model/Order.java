package in.pleb.orders.model;

import java.util.List;

/**
 * Order is a POJO that holds the attributes of an order used in the GraphQL schema
 */

public class Order
{

    public Order(String id) {
        this.id = id;
    }

    public Order(String id, String date, String amount, String customerId, String status, List<Item> items) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.customerId = customerId;
        this.status = status;
        this.items = items;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String id;
    private String date;
    private String amount;
    private String customerId;
    private String status;
    private List <Item> items;

}