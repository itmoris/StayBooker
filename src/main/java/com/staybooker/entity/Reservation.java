package com.staybooker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @EmbeddedId
    private CompositeId id;

    @ToString.Exclude
    @MapsId("apartmentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Apartment apartment;

    @ToString.Exclude
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    @Column(name = "guests", nullable = false)
    private Integer guests;

    @Column(name = "special_requests", nullable = false, length = 500)
    private String specialRequests;
}
