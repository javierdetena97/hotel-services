package com.example.hotel_services.domain.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelCreateRequest {
    @NotNull
    private String hotelName;
}
