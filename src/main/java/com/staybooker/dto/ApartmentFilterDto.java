package com.staybooker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class ApartmentFilterDto {
    private String city;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer guests;
    private Integer page;
}
