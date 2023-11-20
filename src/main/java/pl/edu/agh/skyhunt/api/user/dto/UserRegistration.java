package pl.edu.agh.skyhunt.api.user.dto;

import lombok.Data;

@Data
public class UserRegistration {

    private String name;

    private String surname;

    private String email;

    private String password;

    private String street;

    private String postcode;

    private String country;

    private boolean accept;

    public boolean acceptedConsents() {
        return accept;
    }
}
