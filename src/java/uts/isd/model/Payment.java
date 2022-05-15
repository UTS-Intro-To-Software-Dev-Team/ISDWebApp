package uts.isd.model;

/**
 *
 * @author nexusbaquir
 */
public class Payment {
    private String paymentID;
    private String paymentDate;
    private String cardNumber;
    private String cvv;
    private String fullName;
    private String expiryDate;
    private String method;
    
    public Payment(String paymentID, String paymentDate, String cardNumber, String cvv, String fullName, String expiryDate, String method) {
        this.paymentID = paymentID;
        this.paymentDate = paymentDate;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.fullName = fullName;
        this.expiryDate = expiryDate;
        this.method = method;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String email) {
        this.paymentID = paymentID;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String password) {
        this.paymentDate = paymentDate;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public String getCvv() {
        return cardNumber;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setfullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public String getMethod() {
        return expiryDate;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
