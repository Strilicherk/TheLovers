package com.strilicherk.matchapp.domain.phone_verification;

import com.strilicherk.matchapp.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    private User user;

    @Column(nullable = false)
    @NonNull
    private String verificationCode;

    @Column(nullable = false)
    @NonNull
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    @NonNull
    private LocalDateTime createdAt;
}
