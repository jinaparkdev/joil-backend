package com.spring.joilbackend.model

import arrow.core.None
import arrow.core.Option
import java.util.*

data class User(
    val id: String,
    val name: String,
    val contact: String,
    val expiryDate: Date,
    val note: Option<String> = None
)
