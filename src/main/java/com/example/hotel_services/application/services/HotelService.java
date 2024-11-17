package com.example.hotel_services.application.services;

import com.example.hotel_services.domain.request.HotelCreateRequest;
import com.example.hotel_services.infrastructure.entities.Hotel;
import reactor.core.publisher.Mono;

import java.util.List;

public interface HotelService {
    Mono<Long> createHotel(HotelCreateRequest request);

    Mono<List<Hotel>> getHotels();
}
