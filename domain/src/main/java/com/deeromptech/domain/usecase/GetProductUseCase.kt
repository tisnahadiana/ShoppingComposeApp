package com.deeromptech.domain.usecase

import com.deeromptech.domain.repository.ProductRepository

class GetProductUseCase(private val repository: ProductRepository) {
    suspend fun execute() = repository.getProducts()
}