package com.strilicherk.theloversapi.core.domain.model.user_refresh_token;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "user_refresh_tokens")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class RefreshToken {
    @Id
    @GeneratedValue()
    private UUID id;

    @NonNull
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NonNull
    @Column(name = "device_id", nullable = false, unique = true)
    private String deviceId;

    @NonNull
    @Column(name = "refresh_token", nullable = false, unique = true)
    private String refreshToken;

    @NonNull
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;
}
