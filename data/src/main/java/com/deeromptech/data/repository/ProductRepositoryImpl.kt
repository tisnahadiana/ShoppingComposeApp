package com.deeromptech.data.repository

import com.deeromptech.domain.model.Product
import com.deeromptech.domain.network.NetworkService
import com.deeromptech.domain.network.ResultWrapper
import com.deeromptech.domain.repository.ProductRepository

class ProductRepositoryImpl(private val networkService: NetworkService) : ProductRepository {
    override suspend fun getProducts(category: String?): ResultWrapper<List<Product>> {
        return networkService.getProducts(category)
    }
}