package com.jasonjerome.pulsarapatientsync.di

import com.jasonjerome.pulsarapatientsync.service.LocalClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val mainModule = module(definition = {

    single { LocalClient(androidContext()) }

})