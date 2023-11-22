package pl.edu.agh.skyhunt.bookingcom;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BookingcomApiSettings {

    private final String currencyCode = "PLN";

    private final String languageCode = "pl";

    private final String rapidApiKey = "3068bd2037msh7de961d643d3ed1p1a8dfajsnfcb2ca876087";

    private final String rapidApiHost = "booking-com15.p.rapidapi.com";

    private final String baseUrl = "https://booking-com15.p.rapidapi.com/api/v1";
}
