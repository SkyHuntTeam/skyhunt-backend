package pl.edu.agh.skyhunt.bookingcom.response;

import lombok.Data;

@Data
public class BookingcomAirport {

    private String id;

    private String type;

    private String name;

    private String code;

    private String cityName;

    private String countryName;
}
