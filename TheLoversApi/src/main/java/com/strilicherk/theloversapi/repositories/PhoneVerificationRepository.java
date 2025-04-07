package com.strilicherk.theloversapi.repositories;

import com.strilicherk.theloversapi.domain.phone_verification.PhoneVerification;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneVerificationRepository extends JpaRepository<PhoneVerification, Integer> {
    PhoneVerification findByPhone(@NonNull String phone);
}
