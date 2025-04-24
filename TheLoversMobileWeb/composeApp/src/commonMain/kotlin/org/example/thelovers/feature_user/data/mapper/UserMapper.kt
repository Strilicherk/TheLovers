package org.example.thelovers.feature_user.data.mapper

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.example.thelovers.feature_user.data.dto.UserResponseDTO
import org.example.thelovers.feature_user.domain.model.User
import org.example.thelovers.feature_user.domain.model.UserEntity

object UserMapper{
    fun UserEntity.toUserModel(): User {
        return User(
            id = id,
            name = name,
            email = email,
            phone = phone,
            birthDate = birthDate?.let { LocalDate.parse(it) },
            genderId = genderId,
            roleId = roleId,
            locationId = locationId,
            userVerified = userVerified,
            createdAt = LocalDateTime.parse(createdAt),
            updatedAt = LocalDateTime.parse(updatedAt)
        )
    }

    fun User.toEntityModel(): UserEntity {
        return UserEntity(
            id = id,
            name = name,
            email = email,
            phone = phone,
            birthDate = birthDate?.toString(),
            genderId = genderId,
            roleId = roleId,
            locationId = locationId,
            userVerified = userVerified,
            createdAt = createdAt.toString(),
            updatedAt = updatedAt.toString()
        )
    }

    fun UserResponseDTO.toUserModel(): User {
        return User(
            id = id,
            name = name,
            email = email,
            phone = phone,
            userVerified = userVerified,
            birthDate = birthDate.let { if (it.isNullOrEmpty()) null else LocalDate.parse(it) },
            genderId = genderId,
            locationId = userLocation?.id,
            roleId = roleId,
            createdAt = createdAt.let { LocalDateTime.parse(it) },
            updatedAt = updatedAt.let { LocalDateTime.parse(it) },
        )
    }

    fun UserResponseDTO.toUserEntity(): UserEntity {
        return UserEntity(
            id = id,
            name = name,
            email = email,
            phone = phone,
            userVerified = userVerified,
            birthDate = birthDate,
            genderId = genderId,
            locationId = userLocation?.id,
            roleId = roleId,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}