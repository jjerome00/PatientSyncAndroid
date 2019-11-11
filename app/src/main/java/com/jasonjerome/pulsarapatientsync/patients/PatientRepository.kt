package com.jasonjerome.pulsarapatientsync.patients

import androidx.lifecycle.LiveData
import com.jasonjerome.pulsarapatientsync.dataModels.Patient
import com.jasonjerome.pulsarapatientsync.patients.db.PatientListDAO
import com.jasonjerome.pulsarapatientsync.service.PatientEndpointFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import retrofit2.HttpException
import java.io.IOException

class PatientRepository(
    private val patientListDAO: PatientListDAO
) : KoinComponent {

    fun loadPatients(): LiveData<List<Patient>> {
        loadPatientsFromWeb()
        return loadPatientsFromDB()
    }

    private fun loadPatientsFromDB(): LiveData<List<Patient>> {
        return patientListDAO.getAll()
    }

    private fun loadPatientsFromWeb() {
        val patientEndpoint = PatientEndpointFactory.makeEndpointService()

        GlobalScope.launch {
            try {
                val request = patientEndpoint.getPatientListAsync()
                val response = request.await()
                response.body()?.let { patientList ->
                    patientList.forEach {
                        patientListDAO.insert(it)
                    }
                }
            } catch (e: IOException) {
                error { "io exception: $e" }
            } catch (e: HttpException) {
                error { "http exception: $e" }
            } catch (e: Throwable) {
                error { "throwable exception: $e" }
            }
        }
    }

}