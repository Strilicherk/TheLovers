package org.example.thelovers.feature_location.data

import kotlinx.serialization.Serializable

@Serializable
data class LocationResponseDTO(
    val id: String,
    val city: String,
    val state: String,
    val country: String,
) {
    fun isIncomplete(): Boolean {
        return city.isBlank() || state.isBlank() || country.isBlank()
    }
}

