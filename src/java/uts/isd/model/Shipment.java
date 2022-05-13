package uts.isd.model;

public class Shipment {
    private String shipmentId;
    private String shipmentDate;
    //shipping address
    private String street;
    private String city;
    private String state;
    private String postCode;
    private String method;
    private String orderId;
    
    public Shipment(String method) {
        
        this.method = method;
    }
    
    public Shipment(String shipmentId, String shipmentDate, String street, String city, String state, String postCode, String method, String orderId) {
        this.shipmentId = shipmentId;
        this.shipmentDate = shipmentDate;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
        this.method = method;
        this.orderId = orderId;
    }
    
    public String getMethod() {
        return method;
    }
    
    
    public void setMethod(String method){
        this.method = method;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    
    
    
}

