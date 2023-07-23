package com.example.myplants

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
