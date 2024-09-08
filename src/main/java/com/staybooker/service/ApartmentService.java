package com.staybooker.service;

import com.staybooker.dao.ApartmentDao;
import com.staybooker.model.dto.ApartmentBriefDto;
import com.staybooker.model.dto.ApartmentFilterDto;
import com.staybooker.model.mapper.ApartmentMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ApartmentService {
    private final ApartmentDao apartmentDao = ApartmentDao.getINSTANCE();

    @Getter
    private static final ApartmentService INSTANCE = new ApartmentService();
    private static final Integer LIMIT = 12;

    public List<ApartmentBriefDto> getAll(ApartmentFilterDto filterDto) {
        int offset = LIMIT * (filterDto.getPage() - 1);

        return apartmentDao.getAll(filterDto, offset, LIMIT).stream()
                .map(ApartmentMapper::toApartmentBriefDto)
                .collect(Collectors.toList());
    }

    public long getLastPage(ApartmentFilterDto filterDto) {
        return (long) Math.ceil((double) apartmentDao.countAll(filterDto) / LIMIT);
    }

}
