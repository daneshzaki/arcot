package in.pleb.orders.model;

import java.util.Collection;

public class Item
{

    public Item(String id) {
        this.id = id;
    }

    public Item(String id, String type, String description, String price) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    //utility constructor to populate obj from values
    public Item (Object values)
    {        
        this.values = (Collection)values;
        String[] valueArr = (String[]) values.toArray();
        this.setId(valueArr[0]);
        this.setPrice(valueArr[1]);
        this.setType(valueArr[2]);
        this.setDescription(valueArr[3]);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    private String id;
    private String type;
    private String description;
    private String price;
    private Collection values;

}