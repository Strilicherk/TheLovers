package com.strilicherk.theloversapi.feature_auth.login.authentication;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "users_refresh_token")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class RefreshToken {
    @Id
    @GeneratedValue()
    private UUID id;

    @Column(name = "user_id", nullable = false)
    @NonNull
    private UUID userId;

    @NonNull
    private String deviceId;

    @Column(nullable = false, unique = true)
    @NonNull
    private String refreshToken;

    @Column(nullable = false)
    @NonNull
    private LocalDateTime expiresAt;
}
