package org.example.thelovers.feature_user.create_user.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class User(
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val birthDate: LocalDate? = null,
    val genderId: Int? = null,
    val roleId: Int? = null,
    val locationId: Uuid? = null,
    val userVerified: Boolean = false,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    )
