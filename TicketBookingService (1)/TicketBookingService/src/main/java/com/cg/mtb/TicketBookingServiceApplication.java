package com.cg.mtb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.cg.mtb")
public class TicketBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketBookingServiceApplication.class, args);
		System.out.println("Ticket Booking service is started on port 8013...!");
	}

}
