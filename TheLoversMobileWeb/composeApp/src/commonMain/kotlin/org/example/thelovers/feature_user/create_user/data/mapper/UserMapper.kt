package org.example.thelovers.feature_user.create_user.data.mapper

import org.example.thelovers.feature_user.create_user.data.converters.UserConverters
import org.example.thelovers.feature_user.create_user.data.data_source.api.dto.user.UserDTO
import org.example.thelovers.feature_user.create_user.domain.model.User
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
object UserMapper{
    private val userConverters = UserConverters()

    fun UserDTO.dtoToDomain(): User {
        return User(
            name = this.name,
            email = this.email,
            password = this.password,
            birthDate = this.birthDate,
            genderId = this.genderId,
            roleId = this.roleId,
            locationId = this.locationId,
            userVerified = this.userVerified,
            createdAt = userConverters.fromTimestampToLocalDateTime(this.createdAt),
            updatedAt = userConverters.fromTimestampToLocalDateTime(this.updatedAt),
        )
    }

    fun User.domainToDto(): UserDTO {
        return UserDTO(
            name = this.name,
            email = this.email,
            password = this.password,
            birthDate = this.birthDate,
            genderId = this.genderId,
            roleId = this.roleId,
            locationId = this.locationId,
            userVerified = this.userVerified,
            createdAt = userConverters.fromLocalDateTimeToTimestamp(this.createdAt),
            updatedAt = userConverters.fromLocalDateTimeToTimestamp(this.updatedAt),
        )
    }
}