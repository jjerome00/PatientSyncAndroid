package com.jasonjerome.pulsarapatientsync.di

import androidx.room.Room
import com.jasonjerome.pulsarapatientsync.AppDatabase
import com.jasonjerome.pulsarapatientsync.patients.PatientRepository
import com.jasonjerome.pulsarapatientsync.patients.viewmodels.PatientListViewModel
import com.jasonjerome.pulsarapatientsync.service.LocalClient
import com.jasonjerome.pulsarapatientsync.util.NetworkConnectivity
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val mainModule = module(definition = {

    single { LocalClient(androidContext()) }
    single { NetworkConnectivity(androidContext()) }
    single { PatientRepository(get(), get()) }

    viewModel { PatientListViewModel(get()) }

    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "app-database").build()
    }
    single { get<AppDatabase>().patientListDao() }
})