package com.deeromptech.domain.repository

import com.deeromptech.domain.model.Product
import com.deeromptech.domain.network.ResultWrapper

interface ProductRepository {
    suspend fun getProducts(categoryId: String?): ResultWrapper<List<Product>>
}