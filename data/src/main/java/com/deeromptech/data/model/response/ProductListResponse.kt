package com.deeromptech.data.model.response

import com.deeromptech.data.model.DataProductModel
import com.deeromptech.domain.model.ProductListModel
import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponse(
    val `data`: List<DataProductModel>,
    val msg: String
) {
    fun toProductList() = ProductListModel(
        products = `data`.map { it.toProduct() },
        msg = msg
    )
}
