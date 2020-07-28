package com.sistem_funeraria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@EntityScan( basePackages = {"com.sistem_funeraria.model" } )
public class SistemFunerariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemFunerariaApplication.class, args);
	}

}
