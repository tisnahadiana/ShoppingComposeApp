package com.deeromptech.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CartSummaryResponse(
    val `data`: Summary,
    val msg: String
) {
    fun toCartSummary() = com.deeromptech.domain.model.CartSummary(
        data = `data`.toSummaryData(),
        msg = msg
    )
}
