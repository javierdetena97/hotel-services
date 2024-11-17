package com.example.hotel_services.infrastructure.repositories;

import com.example.hotel_services.infrastructure.entities.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HotelRedisRepository {
    private final ReactiveRedisOperations<String, Hotel> reactiveRedisOperations;

    public Flux<Hotel> findAll() {
        return reactiveRedisOperations.opsForList()
                .range("hotel", 0, -1);
    }

    public Mono<Long> save(Hotel hotel) {
        hotel.setId(UUID.randomUUID().toString());
        return reactiveRedisOperations.opsForList()
                .rightPush("hotel", hotel);
    }
}
