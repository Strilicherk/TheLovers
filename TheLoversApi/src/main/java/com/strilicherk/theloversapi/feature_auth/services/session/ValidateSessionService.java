package com.strilicherk.theloversapi.feature_auth.services.session;

import com.strilicherk.theloversapi.feature_auth.domain.session.ValidateSessionRequestDTO;
import com.strilicherk.theloversapi.feature_auth.domain.session.ValidateSessionResponseDTO;

public interface ValidateSessionService {
    ValidateSessionResponseDTO validateSession(ValidateSessionRequestDTO dto);
}
