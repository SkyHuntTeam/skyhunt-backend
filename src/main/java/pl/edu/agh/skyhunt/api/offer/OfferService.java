package pl.edu.agh.skyhunt.api.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.agh.skyhunt.bookingcom.BookingcomService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;

    private final BookingcomService bookingcomService;

    public Object searchForOffers(String departureQuery, String destinationQuery, LocalDate departureDate) {

        try {
            var offers = bookingcomService.searchForOffers(departureQuery, destinationQuery, departureDate);

            return offers;
        }
        catch(Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
