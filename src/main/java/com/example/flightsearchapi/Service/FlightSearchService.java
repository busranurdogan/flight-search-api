package com.example.flightsearchapi.Service;

import org.springframework.stereotype.Service;

import com.example.flightsearchapi.Model.Airport;
import com.example.flightsearchapi.Model.Flight;
import com.example.flightsearchapi.Repository.FlightRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightSearchService {

    private final FlightRepository flightRepository;

    public FlightSearchService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> searchFlights(Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDate, LocalDateTime returnDate) {
        return flightRepository.searchFlights(departureAirport.getCity(), arrivalAirport.getCity(), departureDate, returnDate);
    }
}
