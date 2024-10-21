package com.deeromptech.domain.network

import com.deeromptech.domain.model.CategoriesListModel
import com.deeromptech.domain.model.Product
import com.deeromptech.domain.model.ProductListModel

interface NetworkService {
    suspend fun getProducts(category:Int?): ResultWrapper<ProductListModel>
    suspend fun getCategories(): ResultWrapper<CategoriesListModel>
}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Failure(val exception: Exception) : ResultWrapper<Nothing>()
}