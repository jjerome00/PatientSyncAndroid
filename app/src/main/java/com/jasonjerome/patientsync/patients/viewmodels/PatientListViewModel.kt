package com.jasonjerome.patientsync.patients.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jasonjerome.patientsync.dataModels.Patient
import com.jasonjerome.patientsync.patients.PatientRepository
import org.jetbrains.anko.AnkoLogger
import org.koin.standalone.KoinComponent

class PatientListViewModel(
    private val patientRepository: PatientRepository
) : ViewModel(), KoinComponent, AnkoLogger {

    private val displayLoadingIndicator = MutableLiveData<Boolean>()
    fun getDisplayLoadingLiveData(): LiveData<Boolean> = displayLoadingIndicator

    fun setLoadingIndicatorDisplay(display: Boolean) {
        displayLoadingIndicator.postValue(display)
    }

    fun getPatients(): LiveData<List<Patient>> {
        setLoadingIndicatorDisplay(true)
        return patientRepository.loadPatients()
    }

}