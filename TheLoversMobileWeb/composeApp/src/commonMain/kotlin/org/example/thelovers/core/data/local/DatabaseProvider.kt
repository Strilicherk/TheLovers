package org.example.thelovers.core.data.local

expect class DatabaseProvider {
    fun getInstance(): AppDatabase
}