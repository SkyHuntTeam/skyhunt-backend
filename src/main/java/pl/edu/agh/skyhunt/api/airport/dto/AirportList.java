package pl.edu.agh.skyhunt.api.airport.dto;

import lombok.Data;

import java.util.List;

@Data
public class AirportList {

    private List<AirportDto> airports;

    public AirportList(List<AirportDto> airports) {
        this.airports = airports;
    }
}
