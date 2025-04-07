package com.strilicherk.theloversapi.domain.user_location;

import java.util.UUID;

public record UserLocationRequestDTO(UUID id, UUID userId, Integer countryId, Integer stateId, Integer cityId, Float latitude, Float longitude) {
}
