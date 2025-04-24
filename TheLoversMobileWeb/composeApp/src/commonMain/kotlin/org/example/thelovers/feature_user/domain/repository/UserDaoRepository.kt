package org.example.thelovers.feature_user.domain.repository

import org.example.thelovers.feature_user.domain.model.UserEntity

interface UserDaoRepository {
    suspend fun getUser(): UserEntity?
    suspend fun upsertUser(userEntity: UserEntity): Boolean
    suspend fun deleteAllUser(): Boolean
}