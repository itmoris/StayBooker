package com.staybooker.validator;

import com.staybooker.dto.ApartmentFilterDto;
import com.staybooker.exception.ValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ApartmentFilterValidator implements Validator<ApartmentFilterDto> {
    @Getter
    private static final ApartmentFilterValidator INSTANCE = new ApartmentFilterValidator();

    @Override
    public void validate(ApartmentFilterDto dto) throws ValidationException {
        LocalDate checkIn = dto.getCheckIn();
        LocalDate checkOut = dto.getCheckOut();

        if (Objects.isNull(checkIn) && Objects.nonNull(checkOut)) {
            throw new ValidationException("checkIn", "Check In and Check Out are required");
        } else if (Objects.isNull(checkOut) && Objects.nonNull(checkIn)) {
            throw new ValidationException("checkOut", "Check In and Check Out are required");
        }

        if (Objects.nonNull(checkIn) && (checkIn.isAfter(checkOut) || checkIn.equals(checkOut))) {
            throw new ValidationException("checkIn", "Check in and Check out are not set correctly");
        }
    }
}
