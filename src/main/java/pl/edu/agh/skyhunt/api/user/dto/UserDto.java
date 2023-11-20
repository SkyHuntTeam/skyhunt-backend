package pl.edu.agh.skyhunt.api.user.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String street;

    private String postcode;

    private String country;
}
