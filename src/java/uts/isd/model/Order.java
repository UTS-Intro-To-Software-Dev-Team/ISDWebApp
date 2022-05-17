package uts.isd.model;

public class Order {
    private int orderID;
    private String itemName;
    private String paymentMethod;
    private String shipmentMethod;
    
    private String status;
    private int quantity;
    private float totalPrice;
    private String date;
    private String street;
    private String city;
    private String state;
    private String postcode;

    public Order(int orderID, String itemName, String paymentMethod, String shipmentMethod, String status, int quantity, float totalPrice, String date, String street, String city, String state, String postcode) {
        this(itemName, paymentMethod, shipmentMethod, status, quantity, totalPrice, date, street, city, state, postcode);
        this.orderID = orderID;
    }

    public Order(String itemName, String paymentMethod, String shipmentMethod, String status, int quantity, float totalPrice, String date, String street, String city, String state, String postcode) {
        this.itemName = itemName;
        this.paymentMethod = paymentMethod;
        this.shipmentMethod = shipmentMethod;
        this.status = status;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getShipmentMethod() {
        return shipmentMethod;
    }

    public String getStatus() {
        return status;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getDate() {
        return date;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getAddress() {
        return street + " " + city + " " + state + " " + postcode;
    }
}
