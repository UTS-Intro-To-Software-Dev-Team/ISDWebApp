package uts.isd.model;

public class Item {
    private int itemID;
    private String item;
    private float price;
    private String type;
    private int stock;

    public Item(int itemID, String item, float price, String type, int stock) {
        this(item, price, type, stock);
        this.itemID = itemID;
    }
    
    public Item(String item, float price, String type, int stock){
        this.item = item;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public int getItemID() {
        return itemID;
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
