package com.strilicherk.theloversapi.core.domain.model.phone_verification;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "phone_verifications")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PhoneVerification {
    @Id
    @GeneratedValue
    private int id;

    @NonNull
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NonNull
    @Column(nullable = false)
    private String phone;

    @NonNull
    @Column(nullable = false)
    private Boolean verified;
}
