package uts.isd.model;

public class Payment {
    private int paymentID;
    private String paymentMethod;
    private String cardNumber;
    private String fullName;
    private String expiryDate;
    private String cvv;
    
    public Payment(int paymentID, String method, String cardNumber, String fullName, String expiryDate, String cvv) {
        this(method, cardNumber, fullName, expiryDate, cvv);
        this.paymentID = paymentID;
    }
    
    public Payment(String method, String cardNumber, String fullName, String expiryDate, String cvv) {
        this.paymentMethod = method;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.fullName = fullName;
        this.expiryDate = expiryDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }
}
