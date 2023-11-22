package pl.edu.agh.skyhunt.api.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.skyhunt.api.offer.dto.OfferList;
import pl.edu.agh.skyhunt.api.offer.dto.OfferSearchCriteria;

@RestController
@RequestMapping("/api/offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/search")
    public OfferList search(@RequestBody OfferSearchCriteria offerSearchCriteria) {

        return new OfferList(offerService.searchForOffers(
                offerSearchCriteria.getDepartureQuery(),
                offerSearchCriteria.getDestinationQuery(),
                offerSearchCriteria.getDepartureDate()
        ));
    }
}
