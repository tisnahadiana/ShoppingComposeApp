package com.deeromptech.domain.repository

import com.deeromptech.domain.model.CartItemModel
import com.deeromptech.domain.model.CartModel
import com.deeromptech.domain.model.CartSummary
import com.deeromptech.domain.model.request.AddCartRequestModel
import com.deeromptech.domain.network.ResultWrapper

interface CartRepository {
    suspend fun addProductToCart(
        request: AddCartRequestModel, userId: Long
    ): ResultWrapper<CartModel>

    suspend fun getCart(userId: Long): ResultWrapper<CartModel>
    suspend fun updateQuantity(cartItemModel: CartItemModel, userId: Long): ResultWrapper<CartModel>
    suspend fun deleteItem(cartItemId: Int, userId: Long): ResultWrapper<CartModel>
    suspend fun getCartSummary(userId: Long): ResultWrapper<CartSummary>
}