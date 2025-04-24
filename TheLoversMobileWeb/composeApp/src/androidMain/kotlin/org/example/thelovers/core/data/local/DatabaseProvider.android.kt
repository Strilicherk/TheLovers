package org.example.thelovers.core.data.local

import android.content.Context
import androidx.room.Room

actual class DatabaseProvider(private val context: Context) {
    private var database: AppDatabase? = null

    actual fun getInstance(): AppDatabase {
        if (database == null) {
            database = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            )
                .addTypeConverter(Converters())
                .build()
        }
        return database!!
    }
}