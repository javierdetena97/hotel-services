package com.example.hotel_services.application.services.impl;

import com.example.hotel_services.application.services.HotelService;
import com.example.hotel_services.domain.request.HotelCreateRequest;
import com.example.hotel_services.infrastructure.entities.Hotel;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final StreamBridge streamBridge;

    @Override
    public Mono<Long> createHotel(HotelCreateRequest request) {
        return Mono.just(request)
                .map(this::mapToEntity)
                .flatMap(this::publishHotel);
    }

    @Override
    public Mono<List<Hotel>> getHotels() {
        return null;
    }

    private Mono<Long> publishHotel(Hotel hotelEntity) {
        return Mono.just(hotelEntity)
                .doOnNext(hotel -> streamBridge.send("writeCache-out-0", hotel))
                .flatMap(hotel -> Mono.just(1L))
                .switchIfEmpty(Mono.just(0L));
    }

    private Hotel mapToEntity(HotelCreateRequest request) {
        return Hotel.builder()
                .name(request.getHotelName())
                .build();
    }
}
