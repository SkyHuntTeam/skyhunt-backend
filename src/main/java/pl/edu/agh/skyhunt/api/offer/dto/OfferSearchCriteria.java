package pl.edu.agh.skyhunt.api.offer.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OfferSearchCriteria {

    private String departureQuery;

    private String destinationQuery;

    private LocalDate departureDate;
}
