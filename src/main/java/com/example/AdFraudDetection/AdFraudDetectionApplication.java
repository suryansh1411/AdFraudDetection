package com.example.AdFraudDetection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;



@SpringBootApplication
public class AdFraudDetectionApplication {

//	private static Jedis jedis;
//	@Autowired
//	AdFraudDetectionApplication(Jedis jedis)
//	{
//		this.jedis = jedis;
//	}

	public static void main(String[] args) {
		SpringApplication.run(AdFraudDetectionApplication.class, args);
		System.out.println("Server is Running");
//		jedis.get("0.0.0.32");
	}
}
