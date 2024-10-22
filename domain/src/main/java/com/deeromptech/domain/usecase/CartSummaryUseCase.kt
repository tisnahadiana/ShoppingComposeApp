package com.deeromptech.domain.usecase

import com.deeromptech.domain.repository.CartRepository

class CartSummaryUseCase (private val repository: CartRepository) {
    suspend fun execute(userId: Long) = repository.getCartSummary(userId)
}