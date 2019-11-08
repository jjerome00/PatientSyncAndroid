package com.jasonjerome.pulsarapatientsync.patients

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.koin.androidx.viewmodel.ext.android.viewModel

class PatientListActivity : AppCompatActivity(), AnkoLogger {

    private val viewModel: PatientListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getPatientList()
    }

}