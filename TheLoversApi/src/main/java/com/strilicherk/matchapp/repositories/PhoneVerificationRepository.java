package com.strilicherk.matchapp.repositories;

import com.strilicherk.matchapp.domain.phone_verification.PhoneVerification;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneVerificationRepository extends JpaRepository<PhoneVerification, Integer> {
    PhoneVerification findByPhone(@NonNull String phone);
}
