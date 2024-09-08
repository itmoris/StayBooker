package com.staybooker.model.entity;

import com.staybooker.model.converter.RoleConverter;
import com.staybooker.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    private Long id;

    @Column(name = "fullname", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @Builder.Default
    @Column(name = "role", nullable = false)
    @Convert(converter = RoleConverter.class)
    private Role role = Role.USER;
}
