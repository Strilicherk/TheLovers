package org.example.thelovers.feature_auth.data

import android.content.Context
import android.util.Base64
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.crypto.tink.Aead
import kotlinx.coroutines.flow.first
import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.Result
import org.example.thelovers.feature_validate_session.domain.Tokens
import org.example.thelovers.core.secure_storage.SecureTokenStorage

class AndroidSecureStorage(
    context: Context,
) : SecureTokenStorage {
    private val Context.dataStore by preferencesDataStore(name = "secure_data")
    private val dataStore = context.dataStore

    private val JWT_TOKEN_KEY = stringPreferencesKey("jwt_token")
    private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    private val aead: Aead = createAead(context)

    private val TAG = "LOGS<AndroidSecureStorage>"

    override suspend fun saveTokens(tokens: Tokens): Result<Unit, DataError.Local> {
        return try {
            val encryptedRefresh = aead.encrypt(tokens.refreshToken.toByteArray(), null)
            val encryptedJwt = aead.encrypt(tokens.jwtToken.toByteArray(), null)

            dataStore.edit { preferences ->
                preferences[JWT_TOKEN_KEY] = Base64.encodeToString(encryptedJwt, Base64.DEFAULT)
                preferences[REFRESH_TOKEN_KEY] = Base64.encodeToString(encryptedRefresh, Base64.DEFAULT)
            }

            Log.d(TAG, "Tokens successfully saved to secure storage.")
            Result.Success(Unit)
        } catch (e: Exception) {
            Log.d(TAG, "Failed to save tokens: ${e.localizedMessage}")
            Result.Error(DataError.Local.TOKEN_SAVE_FAILED)
        }
    }

    override suspend fun getTokens(): Result<Tokens, DataError.Local> {
        return try {
            val preferences = dataStore.data.first()

            val jwtString = preferences[JWT_TOKEN_KEY]
            val refreshString = preferences[REFRESH_TOKEN_KEY]

            if (jwtString == null || refreshString == null) {
                Log.d(TAG, "Tokens not found in secure storage.")
                return Result.Error(DataError.Local.TOKEN_NOT_FOUND)
            }

            val jwt = jwtString.let {
                val bytes = Base64.decode(it, Base64.DEFAULT)
                String(aead.decrypt(bytes, null))
            }

            val refresh = refreshString.let {
                val bytes = Base64.decode(it, Base64.DEFAULT)
                String(aead.decrypt(bytes, null))
            }

            Log.d(TAG, "Tokens successfully retrieved and decrypted.")
            Result.Success(Tokens(jwt, refresh))
        } catch (e: Exception) {
            Log.d(TAG, "Failed to retrieve or decrypt tokens: ${e.localizedMessage}")
            Result.Error(DataError.Local.TOKEN_DECRYPTION_FAILED)
        }
    }

    override suspend fun clearTokens(): Result<Unit, DataError.Local> {
        return try {
            dataStore.edit { preferences ->
                preferences.remove(JWT_TOKEN_KEY)
                preferences.remove(REFRESH_TOKEN_KEY)
            }

            Log.d(TAG, "✅ Tokens successfully cleared from secure storage.")
            Result.Success(Unit)
        } catch (e: Exception) {
            Log.d(TAG, "Failed to clear tokens: ${e.localizedMessage}")
            Result.Error(DataError.Local.TOKEN_CLEAR_FAILED)
        }
    }
}
