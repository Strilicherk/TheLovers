package org.example.thelovers.feature_user.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class User(
    val id: String,
    val name: String?,
    val email: String?,
    val phone: String,
    val birthDate: LocalDate?,
    val genderId: Int?,
    val roleId: Int?,
    val locationId: String?,
    val userVerified: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
