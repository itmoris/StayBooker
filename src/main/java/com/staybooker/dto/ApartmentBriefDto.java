package com.staybooker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApartmentBriefDto {
    private Long id;
    private String title;
    private String location;
    private String price;
    private String imageUrl;
}
