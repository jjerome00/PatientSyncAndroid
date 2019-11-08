package com.jasonjerome.pulsarapatientsync.dataModels

import com.google.gson.Gson
import org.junit.Test

class PatientTest {

    @Test
    fun parse_PatientModel() {
        // GIVEN json of a Patient
        val json = ClassLoader.getSystemResource("patient.json").readText()

        // WHEN the json is parsed into the data class
        val patient = Gson().fromJson(json, Patient::class.java)

        // THEN the parser will not throw an error (failing the test)
    }

}