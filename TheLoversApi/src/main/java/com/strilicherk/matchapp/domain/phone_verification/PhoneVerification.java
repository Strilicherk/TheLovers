package com.strilicherk.matchapp.domain.phone_verification;

import com.strilicherk.matchapp.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table (name = "phone_verifications")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PhoneVerification {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "user_id", nullable = false)
    @NonNull
    private UUID userId;

    @Column(nullable = false)
    @NonNull
    private String phone;

    @Column(nullable = false)
    @NonNull
    private Boolean verified;
}
