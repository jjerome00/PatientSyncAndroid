package com.jasonjerome.patientsync.util

import com.jasonjerome.patientsync.BuildConfig
import org.junit.Test

class BuildConfigTest {

    private val readmeMessage = "See project's README.md for more information."

    @Test
    fun patientEndpointUrl_Present() {
        if (BuildConfig.PATIENT_ENDPOINT.isEmpty()) {
            println("The Patient Endpoint is missing.\n$readmeMessage")
        }

        if (BuildConfig.PATIENT_ENDPOINT.compareTo(BuildConfig.TEST_ENDPOINT, true) == 0) {
            println("The Patient Endpoint is set to a TEST ENDPOINT. A mocked result will be used instead of the (real) server.\n$readmeMessage")
        }

        if (BuildConfig.PATIENT_ENDPOINT.compareTo("http://192.168.1.127:8000/api/", true) == 0) {
            assert(false) { "This is Jason Jerome's test server's ip address - you should change it to your own server's ip address.\n$readmeMessage" }
        }
    }

}