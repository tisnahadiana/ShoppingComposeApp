package com.deeromptech.domain.usecase

import com.deeromptech.domain.model.CartItemModel
import com.deeromptech.domain.repository.CartRepository

class DeleteProductUseCase(private val cartRepository: CartRepository) {
    suspend fun execute(cartItemId: Int, userId: Long) = cartRepository.deleteItem(cartItemId, userId)
}