package com.jasonjerome.patientsync

import android.app.Application
import com.jasonjerome.patientsync.di.mainModule
import org.koin.android.ext.android.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(mainModule))
    }
}