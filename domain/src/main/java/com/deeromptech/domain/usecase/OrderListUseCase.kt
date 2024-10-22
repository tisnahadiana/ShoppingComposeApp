package com.deeromptech.domain.usecase

import com.deeromptech.domain.model.OrdersListModel
import com.deeromptech.domain.repository.OrderRepository

class OrderListUseCase(
    private val repository: OrderRepository
) {
    suspend fun execute() = repository.getOrderList()
}