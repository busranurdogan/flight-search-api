package com.example.flightsearchapi.Scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.flightsearchapi.Model.Airport;
import com.example.flightsearchapi.Model.Flight;
import com.example.flightsearchapi.Repository.AirportRepository;
import com.example.flightsearchapi.Repository.FlightRepository;

@Component
public class FlightDataScheduledJob {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Scheduled(cron = "0 0 3 * * *")
    public void updateFlightData() {
        String mockApiUrl = "https://my.api.mockaroo.com/flights.json?key=aa672470";
        RestTemplate restTemplate = new RestTemplate();
        Flight[] flights = restTemplate.getForObject(mockApiUrl, Flight[].class);

        for (Flight flight : flights) {
            try {
                Airport originAirport = flight.getDepartureAirport();
                Airport destinationAirport = flight.getArrivalAirport();
                if (originAirport != null && destinationAirport != null) {
                    if (!airportRepository.existsById(originAirport.getId())) {
                        airportRepository.save(originAirport);
                        System.out.println("Created airport: " + originAirport);
                    }
                    if (!airportRepository.existsById(destinationAirport.getId())) {
                        airportRepository.save(destinationAirport);
                        System.out.println("Created airport: " + destinationAirport);
                    }
                    flightRepository.save(flight);
                    System.out.println("Updating flight data...");
                } else {
                    System.out.println("Skipping flight with missing airports: " + flight.getId());
                }
            } catch (Exception e) {
                System.err.println("Error saving flight: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
