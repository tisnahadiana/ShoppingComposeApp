package com.deeromptech.domain.di

import com.deeromptech.domain.usecase.AddToCartUseCase
import com.deeromptech.domain.usecase.CartSummaryUseCase
import com.deeromptech.domain.usecase.DeleteProductUseCase
import com.deeromptech.domain.usecase.GetCartUseCase
import com.deeromptech.domain.usecase.GetCategoriesUseCase
import com.deeromptech.domain.usecase.GetProductUseCase
import com.deeromptech.domain.usecase.LoginUseCase
import com.deeromptech.domain.usecase.OrderListUseCase
import com.deeromptech.domain.usecase.PlaceOrderUseCase
import com.deeromptech.domain.usecase.RegisterUseCase
import com.deeromptech.domain.usecase.UpdateQuantityUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetProductUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
    factory { AddToCartUseCase(get()) }
    factory { GetCartUseCase(get()) }
    factory { UpdateQuantityUseCase(get()) }
    factory { DeleteProductUseCase(get()) }
    factory { CartSummaryUseCase(get()) }
    factory { PlaceOrderUseCase(get()) }
    factory { OrderListUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
}