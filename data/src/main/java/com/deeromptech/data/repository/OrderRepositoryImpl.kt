package com.deeromptech.data.repository

import com.deeromptech.domain.model.AddressDomainModel
import com.deeromptech.domain.model.OrdersListModel
import com.deeromptech.domain.network.NetworkService
import com.deeromptech.domain.network.ResultWrapper
import com.deeromptech.domain.repository.OrderRepository

class OrderRepositoryImpl(private val networkService: NetworkService) : OrderRepository {
    override suspend fun placeOrder(addressDomainModel: AddressDomainModel): ResultWrapper<Long> {
        return networkService.placeOrder(addressDomainModel, 1)
    }

    override suspend fun getOrderList(): ResultWrapper<OrdersListModel> {
        return networkService.getOrderList()
    }
}