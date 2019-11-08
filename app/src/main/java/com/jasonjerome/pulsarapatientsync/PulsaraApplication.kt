package com.jasonjerome.pulsarapatientsync

import android.app.Application
import com.jasonjerome.pulsarapatientsync.di.mainModule
import org.koin.android.ext.android.startKoin

class PulsaraApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(mainModule))
    }
}