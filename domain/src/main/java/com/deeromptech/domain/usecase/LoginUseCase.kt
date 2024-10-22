package com.deeromptech.domain.usecase

import com.deeromptech.domain.repository.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {
    suspend fun execute(username: String, password: String) =
        userRepository.login(username, password)
}