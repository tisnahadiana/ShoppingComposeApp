package com.deeromptech.domain.usecase

import com.deeromptech.domain.model.request.AddCartRequestModel
import com.deeromptech.domain.repository.CartRepository

class AddToCartUseCase(private val cartRepository: CartRepository) {
    suspend fun execute(request: AddCartRequestModel, userId: Long) = cartRepository.addProductToCart(request,userId)
}