package com.strilicherk.matchapp.domain.user_location;

import com.strilicherk.matchapp.domain.country.CountryRequestDTO;
import com.strilicherk.matchapp.domain.user.UserRequestDTO;

import java.util.UUID;

public record UserLocationRequestDTO(UUID id, UUID userId, Integer countryId, Integer stateId, Integer cityId, Float latitude, Float longitude) {
}
