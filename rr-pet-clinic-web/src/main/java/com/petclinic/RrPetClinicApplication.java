package com.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.petclinic.repository.sdjpa")
//@EnableMongoRepositories(basePackages = "com.petclinic.repository.mongo")
public class RrPetClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(RrPetClinicApplication.class, args);
	}

}
