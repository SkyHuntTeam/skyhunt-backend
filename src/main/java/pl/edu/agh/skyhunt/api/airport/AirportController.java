package pl.edu.agh.skyhunt.api.airport;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.skyhunt.api.airport.dto.AirportDto;
import pl.edu.agh.skyhunt.api.airport.dto.AirportList;
import pl.edu.agh.skyhunt.api.airport.dto.AirportSearchParams;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/airport")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    private final ModelMapper mapper;

    @GetMapping
    public AirportList allCached() {

        return new AirportList(airportService.getAllAirports()
                .stream()
                .map(a -> mapper.map(a, AirportDto.class))
                .collect(Collectors.toList()));
    }

    @PostMapping("/search")
    public AirportList search(@RequestBody AirportSearchParams airportSearchParams) {

        return new AirportList(airportService.searchForAirports(airportSearchParams.getQuery())
                .stream()
                .map(a -> mapper.map(a, AirportDto.class))
                .collect(Collectors.toList()));
    }
}
