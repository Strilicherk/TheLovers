package org.example.thelovers.feature_user.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String? = null,
    val email: String? = null,
    val phone: String,
    val birthDate: String? = null,
    val genderId: Int? = null,
    val roleId: Int? = null,
    val locationId: String? = null,
    val userVerified: Boolean = false,
    val createdAt: String = Clock.System.now().toLocalDateTime(TimeZone.UTC).toString(),
    val updatedAt: String = Clock.System.now().toLocalDateTime(TimeZone.UTC).toString(),
)
