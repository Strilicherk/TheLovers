package com.strilicherk.theloversapi.feature_auth.services;

import com.strilicherk.theloversapi.feature_auth.domain.sms.dto.PhoneVerificationRequestDTO;
import com.strilicherk.theloversapi.feature_auth.domain.sms.dto.PhoneVerificationResponseDTO;

public interface AuthenticationService {
    PhoneVerificationResponseDTO authenticate(PhoneVerificationRequestDTO phoneVerificationRequestDTO);
}
