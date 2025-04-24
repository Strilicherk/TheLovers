package org.example.thelovers.feature_auth.data

import android.content.Context
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.aead.AesGcmKeyManager
import com.google.crypto.tink.integration.android.AndroidKeysetManager

fun createAead(context: Context): Aead {
    AeadConfig.register()

    val keysetHandle: KeysetHandle = AndroidKeysetManager.Builder()
        .withSharedPref(context, "master_keyset", "secure_prefs")
        .withKeyTemplate(AesGcmKeyManager.aes256GcmTemplate())
        .withMasterKeyUri("android-keystore://secure-data-key")
        .build()
        .keysetHandle

    return keysetHandle.getPrimitive(Aead::class.java)
}