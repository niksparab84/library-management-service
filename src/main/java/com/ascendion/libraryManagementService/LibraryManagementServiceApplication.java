package com.ascendion.libraryManagementService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Library Management Service.
 * This class serves as the entry point for the Spring Boot application.
 * It initializes the application context and starts the embedded server.
 */
@SpringBootApplication
public class LibraryManagementServiceApplication {

	/**
	 * Main method to run the Library Management Service application.
	 * This method initializes the Spring Boot application context and starts the application.
	 * @param args command line arguments (not used in this application)
     */
	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementServiceApplication.class, args);
	}
}
