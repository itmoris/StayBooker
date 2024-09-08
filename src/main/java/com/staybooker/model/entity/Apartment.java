package com.staybooker.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartment_seq")
    @SequenceGenerator(name = "apartment_seq", sequenceName = "apartment_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "location", nullable = false, length = 150)
    private String location;

    @Column(name = "price", nullable = false, length = 50)
    private String price;

    @Column(name = "amenities", nullable = false, length = 100)
    private String amenities;

    @Column(name = "max_guests", nullable = false)
    private Integer maxGuests;

    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @Builder.Default
    @ToString.Exclude
    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
}
