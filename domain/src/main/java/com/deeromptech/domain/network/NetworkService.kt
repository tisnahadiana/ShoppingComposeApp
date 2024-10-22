package com.deeromptech.domain.network

import com.deeromptech.domain.model.AddressDomainModel
import com.deeromptech.domain.model.CartItemModel
import com.deeromptech.domain.model.CartModel
import com.deeromptech.domain.model.CartSummary
import com.deeromptech.domain.model.CategoriesListModel
import com.deeromptech.domain.model.OrdersListModel
import com.deeromptech.domain.model.ProductListModel
import com.deeromptech.domain.model.request.AddCartRequestModel

interface NetworkService {
    suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel>
    suspend fun getCategories(): ResultWrapper<CategoriesListModel>

    suspend fun addProductToCart(
        request: AddCartRequestModel
    ): ResultWrapper<CartModel>

    suspend fun getCart(): ResultWrapper<CartModel>
    suspend fun updateQuantity(cartItemModel: CartItemModel): ResultWrapper<CartModel>
    suspend fun deleteItem(cartItemId: Int, userId: Int): ResultWrapper<CartModel>
    suspend fun getCartSummary(userId: Int): ResultWrapper<CartSummary>
    suspend fun placeOrder(address: AddressDomainModel, userId: Int): ResultWrapper<Long>
    suspend fun getOrderList(): ResultWrapper<OrdersListModel>
}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Failure(val exception: Exception) : ResultWrapper<Nothing>()
}