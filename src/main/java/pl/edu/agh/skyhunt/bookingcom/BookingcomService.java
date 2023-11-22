package pl.edu.agh.skyhunt.bookingcom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.edu.agh.skyhunt.bookingcom.response.BookingcomAirport;
import pl.edu.agh.skyhunt.bookingcom.response.BookingcomAirportsSearchResponse;
import pl.edu.agh.skyhunt.bookingcom.response.BookingcomOffer;
import pl.edu.agh.skyhunt.bookingcom.response.BookingcomOfferSearchResponse;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingcomService {

    private final BookingcomApiSettings apiSettings;

    private void configureHeaders(HttpHeaders headers) {
        headers.set("X-RapidAPI-Key", apiSettings.getRapidApiKey());
        headers.set("X-RapidAPI-Host", apiSettings.getRapidApiHost());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    public List<BookingcomAirport> searchForAirports(String query) throws Exception {

        var response = WebClient.builder()
                .baseUrl(apiSettings.getBaseUrl())
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights/searchDestination")
                        .queryParam("query", query)
                        .queryParam("languagecode", apiSettings.getLanguageCode())
                        .build())
                .headers(this::configureHeaders)
                .retrieve()
                .bodyToMono(BookingcomAirportsSearchResponse.class)
                .block();

        if(response == null || !response.getStatus())  {
            throw new Exception(response == null ? "Error when fetching data" : response.getMessage());
        }

        return response.getData();
    }

    public BookingcomAirport searchForAnyAirport(String query) throws Exception {
        var airports = searchForAirports(query);

        if(airports.isEmpty()) {
            throw new Exception("No airports");
        }

        return airports.get(0);
    }

    public List<BookingcomOffer> searchForOffers(String departureQuery,
                                                  String destinationQuery,
                                                  LocalDate departureDate) throws Exception {

        var departureAirport = searchForAnyAirport(departureQuery);
        var destinationAirport = searchForAnyAirport(destinationQuery);

        var response = WebClient.builder()
                .baseUrl(apiSettings.getBaseUrl())
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights/searchFlights")
                        .queryParam("currency_code", apiSettings.getCurrencyCode())
                        .queryParam("fromId", departureAirport.getId())
                        .queryParam("toId", destinationAirport.getId())
                        .queryParam("departDate", departureDate.toString())
                        .queryParam("pageNo", 1)
                        .queryParam("adults", 1)
                        .build())
                .headers(this::configureHeaders)
                .retrieve()
                .bodyToMono(BookingcomOfferSearchResponse.class)
                .block();

        if(response == null || !response.getStatus())  {
            throw new Exception(response == null ? "Error when fetching data" : response.getMessage());
        }

        return response.getData().getFlightOffers();
    }
}
