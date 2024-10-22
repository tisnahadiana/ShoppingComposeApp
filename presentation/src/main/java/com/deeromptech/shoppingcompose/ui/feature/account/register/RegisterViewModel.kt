package com.deeromptech.shoppingcompose.ui.feature.account.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deeromptech.domain.network.ResultWrapper
import com.deeromptech.domain.usecase.LoginUseCase
import com.deeromptech.domain.usecase.RegisterUseCase
import com.deeromptech.shoppingcompose.ShopperSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState = _registerState

    fun register(email: String, password: String, name: String) {
        _registerState.value = RegisterState.Loading
        viewModelScope.launch {
            when (val response = registerUseCase.execute(email, password, name)) {
                is ResultWrapper.Success -> {
                    ShopperSession.storeUser(response.value)
                    _registerState.value = RegisterState.Success()
                }

                is ResultWrapper.Failure -> {
                    _registerState.value = RegisterState.Error(
                        response.exception.message
                            ?: "Something went wrong!"
                    )
                }
            }
        }
    }
}

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    class Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}