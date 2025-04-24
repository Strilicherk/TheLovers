package org.example.thelovers.feature_user.data.dto

import kotlinx.datetime.LocalDate
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class UserRequestDTO(
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val birthDate: LocalDate? = null,
    val genderId: Int? = null,
    val roleId: Int? = null,
    val locationId: Uuid? = null,
    val userVerified: Boolean = false,
    val createdAt: Long,
    val updatedAt: Long,
)
