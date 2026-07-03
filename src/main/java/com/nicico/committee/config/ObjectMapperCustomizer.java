package com.nicico.committee.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nicico.committee.config.jackson.LocalDateSerializer;
import com.nicico.committee.config.jackson.LocalDateTimeSerializer;
import com.nicico.committee.config.jackson.TimestampToLocalDateDeserializer;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * customize nicico object mapper for this project
 * @author Seyyed
 */
@Configuration
public class ObjectMapperCustomizer {

    private final ObjectMapper objectMapper;

    public ObjectMapperCustomizer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Customizes the Copper's default {@link ObjectMapper} so that it can handle
     * {@link LocalDate} fields.
     */
    @PostConstruct
    public void customizeObjectMapper() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new TimestampToLocalDateDeserializer());
        objectMapper.registerModule(javaTimeModule);
    }
}
