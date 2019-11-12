package com.jasonjerome.patientsync.patients.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.jasonjerome.patientsync.R
import com.jasonjerome.patientsync.dataModels.Patient
import kotlinx.android.synthetic.main.patient_list_row.view.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

class PatientListAdapter(
    private val patients: ArrayList<Patient>,
    private val callback: PatientSelectedCallback
) : RecyclerView.Adapter<PatientListAdapter.PatientHolder>() {

    override fun getItemCount() = patients.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientHolder {
        val inflatedView = parent.inflate(R.layout.patient_list_row, false)
        return PatientHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PatientHolder, position: Int) {
        val patient = patients[position]
        holder.bind(patient)
    }

    inner class PatientHolder(private val view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private lateinit var item: Patient

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) = callback.onClick(item.id)

        fun bind(item: Patient) {
            this.item = item
            view.patient_name.text = item.fullName
            view.pulse.text = item.formattedPulseReading
            view.last_updated.text = item.formattedLastUpdate
        }
    }

}