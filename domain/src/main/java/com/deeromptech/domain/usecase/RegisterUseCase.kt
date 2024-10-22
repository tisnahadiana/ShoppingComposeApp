package com.deeromptech.domain.usecase

import com.deeromptech.domain.repository.UserRepository

class RegisterUseCase(private val userRepository: UserRepository) {
    suspend fun execute(username: String, password: String, name: String) =
        userRepository.register(username, password, name)
}