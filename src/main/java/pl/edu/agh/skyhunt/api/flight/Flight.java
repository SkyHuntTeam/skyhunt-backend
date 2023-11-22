package pl.edu.agh.skyhunt.api.flight;

import jakarta.persistence.*;
import lombok.Data;
import pl.edu.agh.skyhunt.api.airport.Airport;
import pl.edu.agh.skyhunt.api.offer.Offer;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destinationAirport;

    private LocalDate departureDate;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;
}
