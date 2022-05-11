package uts.isd.model;

/**
 *
 * @author nexusbaquir
 */
public class Payment {
    private String paymentID;
    private String paymentDate;
    
    
    public Payment(String paymentID, String paymentDate) {
        this.paymentID = paymentID;
        this.paymentDate = paymentDate;
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
}
