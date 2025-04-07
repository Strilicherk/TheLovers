package org.example.thelovers

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform