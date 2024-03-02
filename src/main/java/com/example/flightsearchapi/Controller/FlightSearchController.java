package com.example.flightsearchapi.Controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.flightsearchapi.Model.Airport;
import com.example.flightsearchapi.Model.Flight;
import com.example.flightsearchapi.Service.FlightSearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@SecurityRequirement(name = "flightsearch")
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    public FlightSearchController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    @Operation(summary = "Search flights")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved flights", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content) })
    @GetMapping("/api/flights/search")
    public List<Flight> searchFlights(
        @Parameter(description = "Departure city") @RequestParam("departure") String departureCity,
        @Parameter(description = "Arrival city") @RequestParam("arrival") String arrivalCity,
        @Parameter(description = "Departure date") @RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDate,
        @Parameter(description = "Return date (optional)") @RequestParam(value = "returnDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDate) {
        Airport departureAirport = new Airport();
        departureAirport.setCity(departureCity);

        Airport arrivalAirport = new Airport();
        arrivalAirport.setCity(arrivalCity);

        return flightSearchService.searchFlights(departureAirport, arrivalAirport, departureDate, returnDate);
    }
}
