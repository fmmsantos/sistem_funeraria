package com.sistem_funeraria.config;

import java.io.IOException;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

@Configuration
public class AppService {

	/*
	@Bean
	ResourceExceptionHandler resourceExceptionHandler() {
		return new ResourceExceptionHandler();
	}
	*/

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer addDefaultViewInclusion() {
		return (builder) -> {
			builder.featuresToEnable(MapperFeature.DEFAULT_VIEW_INCLUSION);
			builder.serializationInclusion(Include.USE_DEFAULTS)
			.serializerByType(Page.class, new JsonPageSerializer());
		};
	}

	public class JsonPageSerializer extends JsonSerializer<Page> {

		public void serialize(Page page, JsonGenerator jsonGen, SerializerProvider serializerProvider)
				throws IOException {
			ObjectMapper om = new ObjectMapper()
					.setSerializationInclusion(Include.USE_DEFAULTS);
			
			jsonGen.writeStartObject();
			jsonGen.writeFieldName("size");
			jsonGen.writeNumber(page.getSize());
			jsonGen.writeFieldName("number");
			jsonGen.writeNumber(page.getNumber());
			jsonGen.writeFieldName("totalElements");
			jsonGen.writeNumber(page.getTotalElements());
			jsonGen.writeFieldName("last");
			jsonGen.writeBoolean(page.isLast());
			jsonGen.writeFieldName("totalPages");
			jsonGen.writeNumber(page.getTotalPages());
			jsonGen.writeObjectField("sort", page.getSort());
			jsonGen.writeFieldName("first");
			jsonGen.writeBoolean(page.isFirst());
			jsonGen.writeFieldName("numberOfElements");
			jsonGen.writeNumber(page.getNumberOfElements());
			jsonGen.writeFieldName("content");
			jsonGen.writeRawValue(
					om.writerWithView(serializerProvider.getActiveView()).writeValueAsString(page.getContent()));
			jsonGen.writeEndObject();

		}
	}
}
