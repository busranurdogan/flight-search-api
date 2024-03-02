package com.example.flightsearchapi.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.flightsearchapi.Model.Flight;
import com.example.flightsearchapi.Service.FlightService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@SecurityRequirement(name = "flightsearch")
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @Operation(summary = "Get all flights")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved all flights", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content) })
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @Operation(summary = "Get a flight by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the flight", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Flight not found", content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(
            @Parameter(description = "ID of flight to be searched") @PathVariable @NonNull Long id) {
        Flight flight = flightService.getFlightById(id);
        if (flight != null) {
            return new ResponseEntity<>(flight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Add a new flight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flight created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content) })
    @PostMapping
    public ResponseEntity<Flight> createFlight(
            @Parameter(description = "Flight to add") @RequestBody @NonNull Flight flight) {
        Flight createdFlight = flightService.createFlight(flight);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing flight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Flight.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Flight not found", content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(
            @Parameter(description = "ID of flight to be updated") @PathVariable @NonNull Long id,
            @Parameter(description = "New flight details") @RequestBody Flight flight) {
        Flight updatedFlight = flightService.updateFlight(id, flight);
        if (updatedFlight != null) {
            return new ResponseEntity<>(updatedFlight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a flight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Flight deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Flight not found", content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(
            @Parameter(description = "ID of flight to be deleted") @PathVariable @NonNull Long id) {
        flightService.deleteFlight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
