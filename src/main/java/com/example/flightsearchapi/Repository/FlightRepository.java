package com.example.flightsearchapi.Repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.flightsearchapi.Model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f " +
           "WHERE f.departureAirport.city= :departure " +
           "AND f.arrivalAirport.city = :arrival " +
           "AND f.departureDateTime = :departureDate " +
           "AND (f.returnDateTime IS NULL OR f.returnDateTime = :returnDate)")
    List<Flight> searchFlights(String departure, String arrival, LocalDateTime departureDate, LocalDateTime returnDate);
}

