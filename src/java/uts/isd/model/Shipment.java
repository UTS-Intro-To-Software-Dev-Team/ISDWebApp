package uts.isd.model;

public class Shipment {
    private String method;

    public Shipment(String method) {
        
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
    public void setMethod(String method){
        this.method = method;
    }
}
