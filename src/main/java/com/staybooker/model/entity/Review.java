package com.staybooker.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @EmbeddedId
    private CompositeId reviewId;

    @ToString.Exclude
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ToString.Exclude
    @MapsId("apartmentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Apartment apartment;

    @Column(name = "review", nullable = false, length = 1000)
    private String review;
}
