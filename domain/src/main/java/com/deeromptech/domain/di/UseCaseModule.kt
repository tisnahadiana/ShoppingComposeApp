package com.deeromptech.domain.di

import com.deeromptech.domain.usecase.AddToCartUseCase
import com.deeromptech.domain.usecase.GetCartUseCase
import com.deeromptech.domain.usecase.GetCategoriesUseCase
import com.deeromptech.domain.usecase.GetProductUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetProductUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
    factory { AddToCartUseCase(get()) }
    factory { GetCartUseCase(get()) }
}