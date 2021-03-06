package uts.isd.controller;

import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.Period;

public class Validator implements Serializable {
    private final String emailPattern = "^([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)$";
    private final String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$";
    private final String namePattern = "^[A-Z][a-z]*$";
    private final String itemPattern = "^[A-Z0-9][A-Za-z0-9]*( [A-Z0-9][A-Za-z0-9]+)*$";
    private final String phonePattern = "^[0-9]{3,15}$";
    private final String postcodePattern = "^[0-9]{4}$";
    private final String cardNumberPattern = "^[0-9]{16}$";
    private final String fullNamePattern = "^[A-Z][a-z]*( [A-Z][a-z]+)+";
    private final String cvvPattern = "^[0-9]{3}$";
    private final String spacedCamelPattern = "^[A-Z0-9][A-Za-z0-9]*( [A-Z0-9][A-Za-z0-9]+)*$";
    
    
    public Validator(HttpSession session) {
        for (String error : new String[] {
             "existErr",
            
            "emailErr", "passErr",
            "nameErr", "firstNameErr", "lastNameErr",
            "phoneErr", "dateErr", "postcodeErr",
            
            "typeErr", "priceErr", "stockErr",
            
            "cardNumberErr", "cvvErr", "paymentMethodErr",
            "fullNameErr", "expiryDateErr",
            
            "shipmentMethodErr",
        }) {
            session.setAttribute(error, null);
        }
    }
    
    private static boolean validate(String pattern, String input) {
        Pattern regEx = Pattern.compile(pattern);
        Matcher match = regEx.matcher(input);
        return match.matches();
    }
    
    public boolean validatePattern(String method, String input) {
        return switch(method) {
            case "email" -> validate(emailPattern, input);
            case "password" -> validate(passwordPattern, input);
            case "name" -> validate(namePattern, input);
            case "item" -> validate(itemPattern, input);
            case "age" -> Period.between(LocalDate.parse(input), LocalDate.now()).getYears() > 13;
            case "phone" -> validate(phonePattern, input);
            case "postcode" -> validate(postcodePattern, input);
            case "cardNumber" -> validate(cardNumberPattern, input);
            case "fullName" -> validate(fullNamePattern, input);
            case "expiryDate" -> Period.between(LocalDate.now(), LocalDate.parse(input)).getDays() > 0;
            case "cvv" -> validate(cvvPattern, input);
            case "spacedCamel" -> validate(spacedCamelPattern, input);
            default -> false;
        };
    }
}
