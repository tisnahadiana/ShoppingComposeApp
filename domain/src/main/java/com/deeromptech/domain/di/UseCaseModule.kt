package com.deeromptech.domain.di

import com.deeromptech.domain.usecase.GetCategoriesUseCase
import com.deeromptech.domain.usecase.GetProductUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetProductUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
}