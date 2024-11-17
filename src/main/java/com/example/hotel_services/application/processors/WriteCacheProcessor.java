package com.example.hotel_services.application.processors;

import com.example.hotel_services.infrastructure.entities.Hotel;
import com.example.hotel_services.infrastructure.repositories.HotelRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
@Slf4j
@RequiredArgsConstructor
public class WriteCacheProcessor implements Function<Flux<Hotel>, Mono<Void>> {
    private final HotelRedisRepository hotelRedisRepository;

    @Override
    public Mono<Void> apply(Flux<Hotel> hotelFlux) {
        return hotelFlux
                .doOnNext(hotel -> log.info("Received hotel: {}", hotel))
                .flatMap(hotelRedisRepository::save)
                .onErrorContinue(this::handleError)
                .then();
    }

    private void handleError(Throwable throwable, Object object) {
        log.error("Error occurred while processing: {}", object, throwable);
    }
}
