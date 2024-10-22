package com.deeromptech.domain.repository

import com.deeromptech.domain.model.AddressDomainModel
import com.deeromptech.domain.model.OrdersListModel
import com.deeromptech.domain.network.ResultWrapper

interface OrderRepository {
    suspend fun placeOrder(addressDomainModel: AddressDomainModel, userId: Long): ResultWrapper<Long>
    suspend fun getOrderList(userId: Long): ResultWrapper<OrdersListModel>
}