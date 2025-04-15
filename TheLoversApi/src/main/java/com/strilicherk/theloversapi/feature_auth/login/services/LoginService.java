package com.strilicherk.theloversapi.feature_auth.login.services;

import com.strilicherk.theloversapi.domain.shared.ResponseDTO;
import com.strilicherk.theloversapi.feature_auth.login.domain.dto.LoginRequestDTO;
import com.strilicherk.theloversapi.feature_auth.login.domain.dto.LoginResponseDTO;

public interface LoginService {
    ResponseDTO<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO);
}
