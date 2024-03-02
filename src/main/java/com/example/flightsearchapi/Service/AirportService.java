package com.example.flightsearchapi.Service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.example.flightsearchapi.Model.Airport;
import com.example.flightsearchapi.Repository.AirportRepository;
import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(@NonNull Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    public Airport createAirport(@NonNull Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport updateAirport(@NonNull Long id, Airport updatedAirport) {
        Airport existingAirport = airportRepository.findById(id).orElse(null);
        if (existingAirport != null) {
            updatedAirport.setId(id);
            return airportRepository.save(updatedAirport);
        }
        return null;
    }

    public void deleteAirport(@NonNull Long id) {
        airportRepository.deleteById(id);
    }
}

