package uts.isd.model;

public class Shipment {
    private String method;
    private String address;

    public Shipment(String method, String address) {
        
        this.method = method;
        this.address = address;
    }

    public String getMethod() {
        return method;
    }
    public void setMethod(String method){
        this.method = method;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
}
