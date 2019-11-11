package com.jasonjerome.pulsarapatientsync.service

import com.jasonjerome.pulsarapatientsync.dataModels.PatientsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PatientEndpointService {
    @GET("patients")
    fun getPatientResponseAsync(): Deferred<Response<PatientsResponse>>
}