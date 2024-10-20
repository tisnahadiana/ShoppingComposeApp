package com.deeromptech.shoppingcompose.di

import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule)
}