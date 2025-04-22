package com.strilicherk.theloversapi.feature_auth.repositories.sms;

import com.strilicherk.theloversapi.core.domain.model.phone_verification.PhoneVerification;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SendSmsCodeRepository extends JpaRepository<PhoneVerification, Integer> {
    PhoneVerification findByPhone(@NonNull String phone);
    PhoneVerification phone(@NonNull String phone);
}
