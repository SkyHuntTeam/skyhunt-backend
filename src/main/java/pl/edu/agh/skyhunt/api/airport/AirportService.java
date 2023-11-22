package pl.edu.agh.skyhunt.api.airport;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.skyhunt.bookingcom.BookingcomService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    private final BookingcomService bookingcomService;

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport saveAirport(Airport airport) {

        return airportRepository.findByCode(airport.getCode())
                .orElse(airportRepository.save(airport));
    }

    public List<Airport> searchForAirports(String query) {

        try {
            var airports = bookingcomService.searchForAirports(query);

            return airports.stream()
                    .map(a -> {
                        Airport airport = new Airport();
                        airport.setCode(a.getCode());
                        airport.setCountry(a.getCountryName());
                        airport.setName(a.getName());
                        airport.setCity(a.getCityName());
                        return airport;
                    })
                    .collect(Collectors.toList());
        }
        catch(Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
