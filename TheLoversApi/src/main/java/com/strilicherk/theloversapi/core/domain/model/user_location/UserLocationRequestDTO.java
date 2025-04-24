package com.strilicherk.theloversapi.core.domain.model.user_location;

import com.strilicherk.theloversapi.core.domain.model.city.City;
import com.strilicherk.theloversapi.core.domain.model.country.Country;
import com.strilicherk.theloversapi.core.domain.model.state.State;

import java.util.UUID;

public record UserLocationRequestDTO(
        UUID id,
        UUID userId,
        Country countryId,
        State stateId,
        City cityId
//        Float latitude,
//        Float longitude
) { }
