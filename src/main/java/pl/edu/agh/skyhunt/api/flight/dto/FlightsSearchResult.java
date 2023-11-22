package pl.edu.agh.skyhunt.api.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.edu.agh.skyhunt.api.flight.Flight;

import java.util.List;

@Data
@AllArgsConstructor
public class FlightsSearchResult {

    private List<Flight> flights;
}
