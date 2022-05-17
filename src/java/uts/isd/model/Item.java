package uts.isd.model;

public class Item {
    private String item;
    private float price;
    private String type;
    private int stock;

    public Item(String item, float price, String type, int stock){
        this.item = item;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public String getItem() {
        return item;
    }

    public float getPrice() {
        return price;
    }

    public String getType(){
        return type;
    }
    public int getStock(){
        return stock;
    }
}
