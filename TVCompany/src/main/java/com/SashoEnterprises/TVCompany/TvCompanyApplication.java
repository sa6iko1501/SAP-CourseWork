package com.SashoEnterprises.TVCompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TvCompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TvCompanyApplication.class, args);
	}

}
