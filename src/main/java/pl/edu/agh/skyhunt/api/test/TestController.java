package pl.edu.agh.skyhunt.api.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import pl.edu.agh.skyhunt.api.flight.Flight;
import pl.edu.agh.skyhunt.api.flight.FlightRepository;
import pl.edu.agh.skyhunt.api.flight.dto.FlightsSearchResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final FlightRepository flightRepository;

    @GetMapping
    public FlightsSearchResult saveMockupFlights() {

        return null;
    }
}
