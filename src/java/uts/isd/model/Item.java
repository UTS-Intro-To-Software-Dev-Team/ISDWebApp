package uts.isd.model;

public class Item {
    private String item;
    private String price;

    public Item(String item, String price){
        this.item = item;
        this.price = price;
    }
    public String getItem(){
        return item;
    }
    public String getPrice(){
        return price;
    }
}
