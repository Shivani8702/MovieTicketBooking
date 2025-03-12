package com.cg.mtb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerForMovieTicketBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerForMovieTicketBookingApplication.class, args);
		System.out.println("Eureka Server Started on port 8761");
	}

}
