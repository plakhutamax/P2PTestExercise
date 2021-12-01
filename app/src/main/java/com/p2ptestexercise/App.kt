package com.p2ptestexercise

import android.app.Application
import com.p2ptestexercise.di.AccountModule
import com.p2ptestexercise.di.LoginModule
import com.p2ptestexercise.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
//            FIXME: Doesn't work with kotlin 1.6.0
//            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.INFO)
            androidContext(this@App)
            modules(
                NetworkModule.create(),
                AccountModule.create(),
                LoginModule.create()
            )
        }
    }
}