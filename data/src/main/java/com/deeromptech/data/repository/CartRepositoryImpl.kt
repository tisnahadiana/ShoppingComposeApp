package com.deeromptech.data.repository

import com.deeromptech.domain.model.CartModel
import com.deeromptech.domain.model.request.AddCartRequestModel
import com.deeromptech.domain.network.NetworkService
import com.deeromptech.domain.network.ResultWrapper
import com.deeromptech.domain.repository.CartRepository

class CartRepositoryImpl(val networkService: NetworkService) : CartRepository {
    override suspend fun addProductToCart(request: AddCartRequestModel): ResultWrapper<CartModel> {
        return networkService.addProductToCart(request)
    }

    override suspend fun getCart(): ResultWrapper<CartModel> {
        return networkService.getCart()
    }
}