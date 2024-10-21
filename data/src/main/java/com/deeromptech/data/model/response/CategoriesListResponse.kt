package com.deeromptech.data.model.response

import com.deeromptech.data.model.CategoryDataModel
import com.deeromptech.domain.model.CategoriesListModel
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesListResponse(
    val `data`: List<CategoryDataModel>,
    val msg: String
) {
    fun toCategoriesList() = CategoriesListModel(
        categories = `data`.map { it.toCategory() },
        msg = msg
    )
}
