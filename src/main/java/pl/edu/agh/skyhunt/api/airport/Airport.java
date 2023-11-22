package pl.edu.agh.skyhunt.api.airport;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Airport {

    @Id
    @GeneratedValue
    private Long id;

    private String code;

    private String name;

    private String city;

    private String country;
}
