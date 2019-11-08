package com.jasonjerome.pulsarapatientsync.patients

import androidx.lifecycle.ViewModel
import com.jasonjerome.pulsarapatientsync.service.PatientEndpointFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.koin.standalone.KoinComponent
import retrofit2.HttpException
import java.io.IOException

class PatientListViewModel : ViewModel(), KoinComponent, AnkoLogger {

    fun getPatientList() {
        val patientEndpoint = PatientEndpointFactory.makeEndpointService()

        GlobalScope.launch {
            try {
                val request = patientEndpoint.getPatientListAsync()
                val response = request.await()

                response.body()?.let {
                    println(it)
                }
            }
            catch (e: IOException) {
                info { "io exception: $e" }
            } catch (e: HttpException) {
                info { "http exception: $e" }
            } catch (e: Throwable) {
                info { "throwable exception: $e" }
            }
        }
    }
}