package com.deeromptech.data.di

import com.deeromptech.data.repository.CartRepositoryImpl
import com.deeromptech.data.repository.CategoryRepositoryImpl
import com.deeromptech.data.repository.ProductRepositoryImpl
import com.deeromptech.domain.repository.CartRepository
import com.deeromptech.domain.repository.CategoryRepository
import com.deeromptech.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<CartRepository> { CartRepositoryImpl(get()) }
}