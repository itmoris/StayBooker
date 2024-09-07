package com.staybooker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CompositeId implements Serializable {
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "apartment_id", nullable = false)
    private Long apartmentId;
}
