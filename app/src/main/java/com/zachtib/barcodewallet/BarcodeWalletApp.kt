package com.zachtib.barcodewallet

import android.app.Application
import com.zachtib.barcodewallet.ui.appModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class BarcodeWalletApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}