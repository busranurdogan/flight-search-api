package com.example.flightsearchapi.Model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "departure_datetime")
    private LocalDateTime departureDateTime;
    
    @Column(name = "return_datetime")
    private LocalDateTime returnDateTime;
    
    @ManyToOne
    @JoinColumn(name = "departure_airport_id", referencedColumnName = "id")
    private Airport departureAirport;
    
    @ManyToOne
    @JoinColumn(name = "arrival_airport_id", referencedColumnName = "id")
    private Airport arrivalAirport;
    
    @Column(name = "price")
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(LocalDateTime returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
