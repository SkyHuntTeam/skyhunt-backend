package pl.edu.agh.skyhunt.api.airport.dto;

import lombok.Data;

@Data
public class AirportDto {

    private Long id;

    private String code;

    private String name;

    private String city;

    private String country;
}
