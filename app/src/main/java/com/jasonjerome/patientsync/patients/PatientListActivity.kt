package com.jasonjerome.patientsync.patients

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jasonjerome.patientsync.R
import com.jasonjerome.patientsync.patients.adapters.PatientListAdapter
import com.jasonjerome.patientsync.patients.adapters.PatientSelectedCallback
import com.jasonjerome.patientsync.patients.viewmodels.PatientListViewModel
import kotlinx.android.synthetic.main.patient_list_activity.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class PatientListActivity : AppCompatActivity(), AnkoLogger {

    private val viewModel: PatientListViewModel by viewModel()

    private val patientSelectedCallback = object : PatientSelectedCallback {
        override fun onClick(patientId: Int) {
            toast("Patient Selected ($patientId)")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patient_list_activity)

        configureListeners()
        configureLiveDataObservers()

        getData()
    }

    private fun getData() {
        viewModel.getPatients().observe(this, Observer { result ->
            val adapter = PatientListAdapter(ArrayList(result), patientSelectedCallback)
            patientListRecycler.adapter = adapter
            viewModel.setLoadingIndicatorDisplay(false)
        })
    }

    private fun configureLiveDataObservers() {
        viewModel.getDisplayLoadingLiveData().observe(this, Observer { displayProgress ->
            swipe_refresh_layout.isRefreshing = displayProgress
        })
    }

    private fun configureListeners() {
        swipe_refresh_layout.setOnRefreshListener {
            getData()
        }
    }
}