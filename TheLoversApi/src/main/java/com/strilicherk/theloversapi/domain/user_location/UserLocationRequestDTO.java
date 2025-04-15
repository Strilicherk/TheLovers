package com.strilicherk.theloversapi.domain.user_location;

import com.strilicherk.theloversapi.domain.city.City;
import com.strilicherk.theloversapi.domain.country.Country;
import com.strilicherk.theloversapi.domain.state.State;

import java.util.UUID;

public record UserLocationRequestDTO(
        UUID id,
        UUID userId,
        Country countryId,
        State stateId,
        City cityId,
        Float latitude,
        Float longitude
) { }
