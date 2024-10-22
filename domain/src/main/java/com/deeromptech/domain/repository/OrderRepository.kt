package com.deeromptech.domain.repository

import com.deeromptech.domain.model.AddressDomainModel
import com.deeromptech.domain.model.OrdersListModel
import com.deeromptech.domain.network.ResultWrapper

interface OrderRepository {
    suspend fun placeOrder(addressDomainModel: AddressDomainModel): ResultWrapper<Long>
    suspend fun getOrderList(): ResultWrapper<OrdersListModel>
}