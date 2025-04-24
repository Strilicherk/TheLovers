package org.example.thelovers.feature_user.data.data_source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import org.example.thelovers.feature_user.domain.model.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Upsert
    suspend fun upsertUser(userEntity: UserEntity): Long

    @Query("DELETE FROM users")
    suspend fun deleteAllUser(): Int
}