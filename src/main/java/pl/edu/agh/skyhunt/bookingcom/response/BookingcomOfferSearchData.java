package pl.edu.agh.skyhunt.bookingcom.response;

import lombok.Data;

import java.util.List;

@Data
public class BookingcomOfferSearchData {

    private List<BookingcomOffer> flightOffers;
}
