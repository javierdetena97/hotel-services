package com.example.hotel_services.infrastructure.config;

import com.example.hotel_services.application.processors.WriteCacheProcessor;
import com.example.hotel_services.infrastructure.entities.Hotel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class EventHandler {
    @Bean
    public Function<Flux<Hotel>, Mono<Void>> redisCacheBinding(final WriteCacheProcessor writeCacheProcessor) {
        return writeCacheProcessor;
    }
}
