package com.strilicherk.theloversapi.feature_auth.phone_verification.repositories;

import com.strilicherk.theloversapi.feature_auth.phone_verification.domain.PhoneVerification;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneVerificationRepository extends JpaRepository<PhoneVerification, Integer> {
    PhoneVerification findByPhone(@NonNull String phone);
}
