package pl.edu.agh.skyhunt.bookingcom.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingcomFlight {

    private BookingcomAirport departureAirport;

    private BookingcomAirport arrivalAirport;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;
}
