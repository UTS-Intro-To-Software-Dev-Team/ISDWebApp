package uts.isd.model;

public class Shipment {
    private int shipmentId;
    private String shipmentMethod;

    public Shipment(int shipmentId, String method) {
        this(method);
        this.shipmentId = shipmentId;
    }

    public Shipment(String method) {
        this.shipmentMethod = method;
    }

    public int getShipmentID() {
        return shipmentId;
    }

    public String getShipmentMethod() {
        return shipmentMethod;
    }
}

