package com.deeromptech.domain.usecase

import com.deeromptech.domain.model.AddressDomainModel
import com.deeromptech.domain.repository.OrderRepository

class PlaceOrderUseCase(val orderRepository: OrderRepository) {
    suspend fun execute(addressDomainModel: AddressDomainModel, userId: Long) =
        orderRepository.placeOrder(addressDomainModel,userId)
}