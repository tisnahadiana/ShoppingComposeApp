package com.deeromptech.shoppingcompose

import android.app.Application
import com.deeromptech.data.di.dataModule
import com.deeromptech.domain.di.domainModule
import com.deeromptech.shoppingcompose.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShoppingApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShoppingApp)
            modules(
                listOf(
                    presentationModule,
                    dataModule,
                    domainModule
                )
            )
        }
    }
}