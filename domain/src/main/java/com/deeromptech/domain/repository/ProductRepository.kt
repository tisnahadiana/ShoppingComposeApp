package com.deeromptech.domain.repository

import com.deeromptech.domain.model.Product
import com.deeromptech.domain.model.ProductListModel
import com.deeromptech.domain.network.ResultWrapper

interface ProductRepository {
    suspend fun getProducts(category:Int?): ResultWrapper<ProductListModel>
}