package com.deeromptech.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserAuthResponse(
    val `data`: UserResponse,
    val msg: String
)