package com.example.flightsearchapi.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.flightsearchapi.Model.Airport;
import com.example.flightsearchapi.Service.AirportService;
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
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @Operation(summary = "Get all airports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved all airports", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Airport.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content) })
    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    @Operation(summary = "Get an airport by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the airport", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Airport.class)) }),
            @ApiResponse(responseCode = "404", description = "Airport not found", content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(
            @Parameter(description = "ID of airport to be searched") @PathVariable Long id) {
        Airport airport = airportService.getAirportById(id);
        if (airport != null) {
            return new ResponseEntity<>(airport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a new airport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Airport created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Airport.class)) }) })
    @PostMapping
    public ResponseEntity<Airport> createAirport(
            @Parameter(description = "Airport to add") @RequestBody Airport airport) {
        Airport createdAirport = airportService.createAirport(airport);
        return new ResponseEntity<>(createdAirport, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing airport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Airport updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Airport.class)) }),
            @ApiResponse(responseCode = "404", description = "Airport not found", content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(
            @Parameter(description = "ID of airport to be updated") @PathVariable Long id,
            @Parameter(description = "New airport details") @RequestBody Airport airport) {
        Airport updatedAirport = airportService.updateAirport(id, airport);
        if (updatedAirport != null) {
            return new ResponseEntity<>(updatedAirport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete an airport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Airport deleted"),
            @ApiResponse(responseCode = "404", description = "Airport not found", content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(
            @Parameter(description = "ID of airport to be deleted") @PathVariable Long id) {
        airportService.deleteAirport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
