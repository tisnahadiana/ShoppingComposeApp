package com.deeromptech.data.di

import com.deeromptech.data.repository.CartRepositoryImpl
import com.deeromptech.data.repository.CategoryRepositoryImpl
import com.deeromptech.data.repository.OrderRepositoryImpl
import com.deeromptech.data.repository.ProductRepositoryImpl
import com.deeromptech.data.repository.UserRepositoryImpl
import com.deeromptech.domain.repository.CartRepository
import com.deeromptech.domain.repository.CategoryRepository
import com.deeromptech.domain.repository.OrderRepository
import com.deeromptech.domain.repository.ProductRepository
import com.deeromptech.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<CartRepository> { CartRepositoryImpl(get()) }
    single<OrderRepository> { OrderRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
}