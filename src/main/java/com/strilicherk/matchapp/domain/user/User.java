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

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true, nullable = false)
    @NonNull
    private String phone;

    private Boolean userVerified;

    private LocalDate birthDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "location_id")
    private UserLocation location;
}
