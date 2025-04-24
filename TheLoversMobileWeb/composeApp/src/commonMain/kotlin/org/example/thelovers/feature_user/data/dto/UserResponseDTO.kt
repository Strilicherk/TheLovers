package org.example.thelovers.feature_user.data.dto

import kotlinx.serialization.Serializable
import org.example.thelovers.core.domain.model.Gender
import org.example.thelovers.feature_location.data.LocationResponseDTO

@Serializable
data class UserResponseDTO(
    val id: String,
    val name: String?,
    val email: String?,
    val phone: String,
    val userVerified: Boolean,
    val birthDate: String?,
    val genderId: Int?,
    val userLocation: LocationResponseDTO?,
    val roleId: Int,
    val createdAt: String,
    val updatedAt: String
) {
    fun isIncomplete(): Boolean {
        if (name.isNullOrBlank()) return true
        if (birthDate == null) return true
        if (userLocation != null && userLocation.isIncomplete()) return true
        if (genderId != null && Gender.fromId(genderId) == null) return true
        return false
    }
}






