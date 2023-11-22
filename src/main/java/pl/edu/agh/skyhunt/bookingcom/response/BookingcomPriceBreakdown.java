package pl.edu.agh.skyhunt.bookingcom.response;

import lombok.Data;

@Data
public class BookingcomPriceBreakdown {

    private BookingcomPrice total;

    private BookingcomPrice totalWithoutDiscount;
}
