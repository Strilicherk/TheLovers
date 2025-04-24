package org.example.thelovers.feature_user.data.repository

import org.example.thelovers.feature_user.data.data_source.UserDao
import org.example.thelovers.feature_user.domain.model.UserEntity
import org.example.thelovers.feature_user.domain.repository.UserDaoRepository

class UserDaoRepositoryImpl(
    private val db: UserDao
): UserDaoRepository {
    override suspend fun getUser(): UserEntity? {
        return db.getUser()
    }

    override suspend fun upsertUser(userEntity: UserEntity): Boolean {
        return db.upsertUser(userEntity) != 0L
    }

    override suspend fun deleteAllUser(): Boolean {
        return db.deleteAllUser() != 0
    }

}