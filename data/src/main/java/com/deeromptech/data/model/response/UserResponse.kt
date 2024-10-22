package com.deeromptech.data.model.response

import com.deeromptech.domain.model.UserDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Int?,
    val username: String,
    val email: String,
    val name: String
) {
    fun toDomainModel() = UserDomainModel(
        id = id,
        username = username,
        email = email,
        name = name
    )
}
