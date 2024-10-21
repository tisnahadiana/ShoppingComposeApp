package com.deeromptech.shoppingcompose.di

import com.deeromptech.shoppingcompose.ui.feature.cart.CartViewModel
import com.deeromptech.shoppingcompose.ui.feature.home.HomeViewModel
import com.deeromptech.shoppingcompose.ui.feature.product_details.ProductDetailsViewModel
import com.deeromptech.shoppingcompose.ui.feature.summary.CartSummaryViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel {
        ProductDetailsViewModel(get())
    }
    viewModel {
        CartViewModel(get(),get(),get())
    }
    viewModel {
        CartSummaryViewModel(get())
    }
}