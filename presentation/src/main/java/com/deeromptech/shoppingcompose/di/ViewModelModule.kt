package com.deeromptech.shoppingcompose.di

import com.deeromptech.shoppingcompose.ui.feature.account.login.LoginViewModel
import com.deeromptech.shoppingcompose.ui.feature.account.register.RegisterViewModel
import com.deeromptech.shoppingcompose.ui.feature.cart.CartViewModel
import com.deeromptech.shoppingcompose.ui.feature.home.HomeViewModel
import com.deeromptech.shoppingcompose.ui.feature.orders.OrdersViewModel
import com.deeromptech.shoppingcompose.ui.feature.product_details.ProductDetailsViewModel
import com.deeromptech.shoppingcompose.ui.feature.summary.CartSummaryViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel {
        ProductDetailsViewModel(get(),get())
    }
    viewModel {
        CartViewModel(get(), get(), get(),get())
    }
    viewModel {
        CartSummaryViewModel(get(), get(),get())
    }
    viewModel {
        OrdersViewModel(get(),get())
    }

    viewModel {
        LoginViewModel(get(),get())
    }

    viewModel {
        RegisterViewModel(get(),get())
    }
}