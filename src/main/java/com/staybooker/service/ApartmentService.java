package com.staybooker.service;

import com.staybooker.dao.ApartmentDao;
import com.staybooker.dto.ApartmentBriefDto;
import com.staybooker.dto.ApartmentFilterDto;
import com.staybooker.mapper.ApartmentMapper;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class ApartmentService {
    private final ApartmentDao apartmentDao = ApartmentDao.getINSTANCE();

    @Getter
    private static final ApartmentService INSTANCE = new ApartmentService();
    private static final Integer LIMIT = 12;

    private ApartmentService() {
    }

    public List<ApartmentBriefDto> getAll(ApartmentFilterDto filterDto) {
        int offset = LIMIT * (filterDto.getPage()-1);

        return apartmentDao.getAll(filterDto, offset, LIMIT).stream()
                .map(ApartmentMapper::toApartmentBriefDto)
                .collect(Collectors.toList());
    }

    public long getLastPage(ApartmentFilterDto filterDto) {
        return (long) Math.ceil((double) apartmentDao.countAll(filterDto) / LIMIT);
    }

}
