package pl.edu.agh.skyhunt.api.offer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import pl.edu.agh.skyhunt.api.airport.Airport;
import pl.edu.agh.skyhunt.api.flight.Flight;

import java.util.List;

@Entity
@Data
public class Offer {

    @Id
    @GeneratedValue
    private Long id;

    private String bookingcomToken;

    private Integer totalPrice;

    private Integer totalWithoutDiscount;

    @OneToMany
    private List<Flight> flights;
}
