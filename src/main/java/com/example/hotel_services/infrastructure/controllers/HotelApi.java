package com.example.hotel_services.infrastructure.controllers;

import com.example.hotel_services.domain.request.HotelCreateRequest;
import com.example.hotel_services.infrastructure.entities.Hotel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.util.List;

@RequestMapping("/api/hotel")
public interface HotelApi {
    @PostMapping
    Mono<ResponseEntity<Long>> createHotel(@RequestBody @Valid HotelCreateRequest request);

    @GetMapping
    Mono<ResponseEntity<List<Hotel>>> getHotels();
}
