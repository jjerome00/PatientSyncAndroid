package com.jasonjerome.patientsync.patients

import androidx.lifecycle.LiveData
import com.jasonjerome.patientsync.dataModels.Patient
import com.jasonjerome.patientsync.patients.db.PatientListDAO
import com.jasonjerome.patientsync.service.PatientEndpointFactory
import com.jasonjerome.patientsync.util.NetworkConnectivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import retrofit2.HttpException
import java.io.IOException

class PatientRepository(
    private val patientListDAO: PatientListDAO,
    private val network: NetworkConnectivity
) : KoinComponent {

    fun loadPatients(): LiveData<List<Patient>> {
        if (network.isNetworkAvailable()) {
            loadPatientsFromWeb()
        }
        return loadPatientsFromDB()
    }

    private fun loadPatientsFromDB(): LiveData<List<Patient>> {
        return patientListDAO.getAll()
    }

    private fun loadPatientsFromWeb() {
        val patientEndpoint = PatientEndpointFactory.makeEndpointService()

        GlobalScope.launch {
            try {
                val request = patientEndpoint.getPatientResponseAsync()
                val response = request.await()
                response.body()?.let { responseObject ->
                    if (responseObject.message.compareTo("success")  == 0) {
                        val patientList = responseObject.data
                        patientList.forEach {
                            patientListDAO.insert(it)
                        }
                    }
                }
            } catch (e: IOException) {
                cancel(e.localizedMessage)
            } catch (e: HttpException) {
                cancel(e.localizedMessage)
            } catch (e: Throwable) {
                cancel(e.localizedMessage)
            }
        }
    }

}