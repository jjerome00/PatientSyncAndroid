package com.jasonjerome.pulsarapatientsync.patients.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jasonjerome.pulsarapatientsync.patients.adapters.PatientListAdapter
import com.jasonjerome.pulsarapatientsync.patients.adapters.PatientSelectedCallback
import com.jasonjerome.pulsarapatientsync.service.PatientEndpointFactory
import com.jasonjerome.pulsarapatientsync.util.Event
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.koin.standalone.KoinComponent
import retrofit2.HttpException
import java.io.IOException

class PatientListViewModel : ViewModel(), KoinComponent, AnkoLogger {

    private val patientListLiveData = MutableLiveData<Event<PatientListAdapter>>()
    private val displayLoadingIndicator = MutableLiveData<Boolean>()

    fun getPatientListLiveData(): MutableLiveData<Event<PatientListAdapter>> = patientListLiveData
    fun getDisplayLoadingLiveData(): LiveData<Boolean> = displayLoadingIndicator


    private fun setLoadingIndicatorDisplay(display: Boolean) {
        displayLoadingIndicator.postValue(display)
    }

    fun getPatientList(callback: PatientSelectedCallback) {
        setLoadingIndicatorDisplay(true)
        val patientEndpoint = PatientEndpointFactory.makeEndpointService()

        GlobalScope.launch {
            try {
                val request = patientEndpoint.getPatientListAsync()
                val response = request.await()

                response.body()?.let {
                    val adapter = PatientListAdapter(ArrayList(it), callback)
                    patientListLiveData.postValue(Event(adapter))
                    setLoadingIndicatorDisplay(false)
                }
            } catch (e: IOException) {
                info { "io exception: $e" }
                setLoadingIndicatorDisplay(false)
            } catch (e: HttpException) {
                info { "http exception: $e" }
                setLoadingIndicatorDisplay(false)
            } catch (e: Throwable) {
                info { "throwable exception: $e" }
                setLoadingIndicatorDisplay(false)
            }
        }
    }

}