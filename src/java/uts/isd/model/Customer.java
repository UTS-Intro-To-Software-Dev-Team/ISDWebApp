package uts.isd.model;

public class Customer {
    private int customerID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String dob;
    private String phone;
    
    private String street;
    private String city;
    private String state;
    private String postcode;
    private boolean isStaff;
    private boolean isActive;

    public Customer(int customerID, String email, String password, String firstName, String lastName, String dob, String phone, String street, String city, String state, String postcode, boolean isStaff, boolean isActive) {
        this(email, password, firstName, lastName, dob, phone, street, city, state, postcode);
        this.customerID = customerID;
        this.isStaff = isStaff;
        this.isActive = isActive;
    }
    
    public Customer(String email, String password, String firstName, String lastName, String dob, String phone, String street, String city, String state, String postcode) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
    }

    public int getCustomerID() {
        return customerID;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
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

    public boolean isIsStaff() {
        return isStaff;
    }

    public boolean isIsActive() {
        return isActive;
    }
}
