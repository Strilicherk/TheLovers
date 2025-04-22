package com.strilicherk.theloversapi.feature_auth.services.session;

import com.strilicherk.theloversapi.core.security.jwt.TokenGenerator;
import com.strilicherk.theloversapi.core.security.jwt.TokenStore;
import com.strilicherk.theloversapi.core.security.jwt.TokenValidator;
import com.strilicherk.theloversapi.feature_auth.domain.session.ValidateSessionRequestDTO;
import com.strilicherk.theloversapi.feature_auth.domain.session.ValidateSessionResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidateSessionServiceImpl implements ValidateSessionService {
    private final TokenValidator tokenValidator;
    private final TokenGenerator tokenGenerator;
    private final TokenStore tokenStore;

    @Autowired
    public ValidateSessionServiceImpl(TokenGenerator tokenGenerator, TokenValidator tokenValidator, TokenStore tokenStore) {
        this.tokenGenerator = tokenGenerator;
        this.tokenValidator = tokenValidator;
        this.tokenStore = tokenStore;
    }

    @Override
    public ValidateSessionResponseDTO validateSession(ValidateSessionRequestDTO dto) {
        if (!tokenValidator.isRefreshTokenValid(dto.refreshToken(), dto.deviceId())) {
            log.info("RefreshToken is not valid.");
            return new ValidateSessionResponseDTO(false, null, null);
        }

        String newRefreshToken = tokenGenerator.generateRefreshToken(dto.userId(), dto.deviceId());
        String newJwtToken = tokenGenerator.generateJwtToken(dto.userId());
        tokenStore.storeRefreshToken(dto.userId(), newRefreshToken, dto.deviceId());
        return new ValidateSessionResponseDTO(true, newRefreshToken, newJwtToken);
    }
}
