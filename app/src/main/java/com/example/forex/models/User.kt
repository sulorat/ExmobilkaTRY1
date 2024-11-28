package com.example.forex.models

data class User(
    val name: String,
    val emailOrPhone: String,
    var password: String
)

