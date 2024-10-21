package com.deeromptech.shoppingcompose.ui.feature.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deeromptech.domain.model.request.AddCartRequestModel
import com.deeromptech.domain.usecase.AddToCartUseCase
import com.deeromptech.shoppingcompose.model.UiProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(val useCase: AddToCartUseCase) : ViewModel() {

    private val _state = MutableStateFlow<ProductDetailsEvent>(ProductDetailsEvent.Nothing)
    val state = _state.asStateFlow()

    fun addProductToCart(product: UiProductModel) {
        viewModelScope.launch {
            _state.value = ProductDetailsEvent.Loading
            val result = useCase.execute(
                AddCartRequestModel(
                    product.id,
                    product.title,
                    product.price,
                    1,
                    1
                )
            )
            when (result) {
                is com.deeromptech.domain.network.ResultWrapper.Success -> {
                    _state.value = ProductDetailsEvent.Success("Product added to cart")
                }

                is com.deeromptech.domain.network.ResultWrapper.Failure -> {
                    _state.value = ProductDetailsEvent.Error("Something went wrong!")
                }
            }
        }
    }


}

sealed class ProductDetailsEvent {
    data object Loading : ProductDetailsEvent()
    data object Nothing : ProductDetailsEvent()
    data class Success(val message: String) : ProductDetailsEvent()
    data class Error(val message: String) : ProductDetailsEvent()
}