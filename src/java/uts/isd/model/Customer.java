package uts.isd.model;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    public Customer(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean ComparePassword(String password) {
        return this.password.equals(password);
    }
}
