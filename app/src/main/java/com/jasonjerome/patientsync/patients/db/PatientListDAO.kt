package com.jasonjerome.patientsync.patients.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.jasonjerome.patientsync.dataModels.Patient

@Dao
interface PatientListDAO {

    @Query("select * from patient_list")
    fun getAll(): LiveData<List<Patient>>

    @Insert(onConflict = REPLACE)
    fun insert(vararg patient: Patient)

    @Delete
    fun delete(patient: Patient)

    @Query("delete from patient_list")
    fun deleteAll()
}