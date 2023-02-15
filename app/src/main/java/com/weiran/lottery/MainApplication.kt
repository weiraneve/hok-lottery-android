package com.weiran.lottery

import android.app.Application
import com.weiran.lottery.common.di.networkModule
import com.weiran.lottery.common.di.serviceModule
import com.weiran.lottery.common.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    networkModule,
                    viewModelModule,
                    serviceModule
                )
            )
        }
    }
}