package com.deeromptech.data.di

import com.deeromptech.data.repository.ProductRepositoryImpl
import com.deeromptech.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
}