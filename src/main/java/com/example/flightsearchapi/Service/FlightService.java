package com.example.flightsearchapi.Service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.flightsearchapi.Model.Flight;
import com.example.flightsearchapi.Repository.FlightRepository;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // Tüm uçuşları getir
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // ID ile uçuş getir
    public Flight getFlightById(@NonNull Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    // Yeni bir uçuş oluştur
    public Flight createFlight(@NonNull Flight flight) {
        return flightRepository.save(flight);
    }

    // Uçuşu güncelle
    public Flight updateFlight(@NonNull Long id, Flight updatedFlight) {
        Flight existingFlight = flightRepository.findById(id).orElse(null);
        if (existingFlight != null) {
            updatedFlight.setId(id);
            return flightRepository.save(updatedFlight);
        }
        return null;
    }

    // Uçuşu sil
    public void deleteFlight(@NonNull Long id) {
        flightRepository.deleteById(id);
    }

    
    public void saveFlight(@NonNull Flight flight) {
        flightRepository.save(flight);
    }
}
