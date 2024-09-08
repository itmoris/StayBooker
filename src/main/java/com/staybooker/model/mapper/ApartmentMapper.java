package com.staybooker.model.mapper;

import com.staybooker.model.dto.ApartmentBriefDto;
import com.staybooker.model.dto.ApartmentFilterDto;
import com.staybooker.model.entity.Apartment;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

import static com.staybooker.util.ParseUtil.*;

@UtilityClass
public class ApartmentMapper {
    public static ApartmentBriefDto toApartmentBriefDto(Apartment apartment) {
        return ApartmentBriefDto.builder()
                .id(apartment.getId())
                .title(apartment.getTitle())
                .location(apartment.getLocation())
                .price(apartment.getPrice())
                .imageUrl(apartment.getImageUrl())
                .build();
    }

    public static ApartmentFilterDto toApartmentFilterDto(HttpServletRequest request) {
        return ApartmentFilterDto.builder()
                .city(parseString(request.getParameter("city"), null))
                .checkIn(parseDate(request.getParameter("checkIn"), null))
                .checkOut(parseDate(request.getParameter("checkOut"), null))
                .guests(parseInt(request.getParameter("guests"),null))
                .page(parseInt(request.getParameter("page"), 1))
                .build();
    }
}
