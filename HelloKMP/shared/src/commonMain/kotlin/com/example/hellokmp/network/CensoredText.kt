package com.example.hellokmp.network

import kotlinx.serialization.Serializable

@Serializable
data class CensoredText(
    val result: String
)
