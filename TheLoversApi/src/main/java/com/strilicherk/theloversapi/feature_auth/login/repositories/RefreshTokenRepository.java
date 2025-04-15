package com.strilicherk.theloversapi.feature_auth.login.repositories;

import com.strilicherk.theloversapi.feature_auth.login.authentication.RefreshToken;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByRefreshToken(@NonNull String deviceId);
    void deleteByUserIdAndDeviceId(UUID id, String deviceId);
}
