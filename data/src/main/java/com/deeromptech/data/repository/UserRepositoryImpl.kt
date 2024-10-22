package com.deeromptech.data.repository

import com.deeromptech.domain.network.NetworkService
import com.deeromptech.domain.repository.UserRepository

class UserRepositoryImpl(private val networkService: NetworkService) : UserRepository {
    override suspend fun register(email: String, password: String, name: String) =
        networkService.register(email, password, name)

    override suspend fun login(email: String, password: String) =
        networkService.login(email, password)
}