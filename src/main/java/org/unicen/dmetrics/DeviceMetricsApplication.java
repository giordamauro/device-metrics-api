package org.unicen.dmetrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Main Class. Holds the default Spring Boot default configuration.  
 *
 */
@SpringBootApplication
public class DeviceMetricsApplication {

    /**
     * Main method used to start up the Spring Boot API.
     * @param args
     */
	public static void main(String[] args) {

	    SpringApplication.run(DeviceMetricsApplication.class, args);
	}
}
