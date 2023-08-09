package com.example.AdFraudDetection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class AdFraudDetectionApplication {


	public static void main(String[] args) {
		SpringApplication.run(AdFraudDetectionApplication.class, args);
		System.out.println("Server is Running");
	}
}
