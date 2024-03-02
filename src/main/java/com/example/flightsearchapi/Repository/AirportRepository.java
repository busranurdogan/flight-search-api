package com.example.flightsearchapi.Repository;

import com.example.flightsearchapi.Model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
}
