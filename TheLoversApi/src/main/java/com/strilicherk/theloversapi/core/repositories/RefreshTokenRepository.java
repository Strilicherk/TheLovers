package com.strilicherk.theloversapi.core.repositories;

import com.strilicherk.theloversapi.core.domain.model.user_refresh_token.RefreshToken;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByRefreshToken(@NonNull String refreshToken);
    void deleteByUserIdAndDeviceId(UUID id, String deviceId);
}
