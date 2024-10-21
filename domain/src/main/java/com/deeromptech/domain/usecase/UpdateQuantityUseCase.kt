package com.deeromptech.domain.usecase

import com.deeromptech.domain.model.CartItemModel
import com.deeromptech.domain.repository.CartRepository

class UpdateQuantityUseCase(private val cartRepository: CartRepository) {
    suspend fun execute(cartItemModel: CartItemModel) = cartRepository.updateQuantity(cartItemModel)
}