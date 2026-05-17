package com.sad.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShipmentAnomalyDetectorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipmentAnomalyDetectorApiApplication.class, args);
		System.out.println("ShipmentAnomalyDetectorApiApplication started");
	}

}
