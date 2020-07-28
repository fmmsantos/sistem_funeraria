package com.sistem_funeraria.config;

import java.time.LocalDate;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Configuration
public class JavaTimeModuleConfig {
	
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer objectMapperBuilderCustomizer() {
		return (Jackson2ObjectMapperBuilder builder) -> {
			builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			// builder.serializerByType(LocalDate.class, LocalDateSerializer.class);
			builder.modulesToInstall(new JavaTimeModule());
		};
	}
	
}
