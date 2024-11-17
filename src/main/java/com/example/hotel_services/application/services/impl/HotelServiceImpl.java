package com.example.hotel_services.application.services.impl;

import com.example.hotel_services.application.services.HotelService;
import com.example.hotel_services.domain.request.HotelCreateRequest;
import com.example.hotel_services.infrastructure.entities.Hotel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Override
    public Mono<Long> createHotel(HotelCreateRequest request) {
        return null;
    }

    @Override
    public Mono<List<Hotel>> getHotels() {
        return null;
    }
}
