package com.example.flightsearchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "Flight Seach API", version = "1.0", description = "", contact = @Contact(name = "Büşra Nur Doğan", url = "https://github.com/busranurdogan/flight-search-api", email = "busranurdoan@gmail.com")))
@SecurityScheme(name = "flightsearch", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class FlightSearchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightSearchApiApplication.class, args);
	}

}
