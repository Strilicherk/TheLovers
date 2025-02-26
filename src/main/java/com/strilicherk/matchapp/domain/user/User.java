package com.strilicherk.matchapp.domain.user;

import com.strilicherk.matchapp.domain.gender.Gender;
import com.strilicherk.matchapp.domain.user_location.UserLocation;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Table(name = "users")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @NonNull
    private String name;

    @Column(unique = true, nullable = false)
    @NonNull
    private String email;

    @Column(nullable = false)
    @NonNull
    private String passwordHash;

    @Column(unique = true, nullable = false)
    @NonNull
    private String phone;

    @Column(nullable = false)
    @NonNull
    private Boolean userVerified;

    @Column(nullable = false)
    @NonNull
    private LocalDate birthDate;

    @Column(nullable = false)
    @NonNull
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @NonNull
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    @NonNull
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "location_id", nullable = false)
    @NonNull
    private UserLocation location;
}
