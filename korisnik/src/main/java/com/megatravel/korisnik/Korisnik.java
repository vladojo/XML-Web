package com.megatravel.korisnik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Korisnik {

	public static void main(String[] args) {
		SpringApplication.run(Korisnik.class, args);
	}

}
