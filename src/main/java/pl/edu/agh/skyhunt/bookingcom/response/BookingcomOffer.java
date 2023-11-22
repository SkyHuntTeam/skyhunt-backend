package pl.edu.agh.skyhunt.bookingcom.response;

import lombok.Data;

import java.util.List;

@Data
public class BookingcomOffer {

    private String token;

    private List<BookingcomFlight> segments;

    private BookingcomPriceBreakdown priceBreakdown;
}
