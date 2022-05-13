package uts.isd.model;

public class Item {
    private String item;
    private Float price;

    public Item(String item, Float price){
        this.item = item;
        this.price = price;
    }
    public getItem(){
        return item;
    }
    public getPrice(){
        return price;
    }
}
