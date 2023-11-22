package pl.edu.agh.skyhunt.api.offer.dto;

import lombok.Data;

import java.util.List;

@Data
public class OfferList {

    private Object offers;

    public OfferList(Object offers) {
        this.offers = offers;
    }
}
