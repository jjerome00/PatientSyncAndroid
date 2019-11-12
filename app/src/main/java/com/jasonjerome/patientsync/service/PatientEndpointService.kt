package com.jasonjerome.patientsync.service

import com.jasonjerome.patientsync.dataModels.PatientsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PatientEndpointService {
    @GET("patients")
    fun getPatientResponseAsync(): Deferred<Response<PatientsResponse>>
}