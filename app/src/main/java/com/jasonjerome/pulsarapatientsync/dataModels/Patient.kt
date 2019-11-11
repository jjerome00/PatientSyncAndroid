package com.jasonjerome.pulsarapatientsync.dataModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "patient_list")
data class Patient (
    @ColumnInfo(name="id") @PrimaryKey val id: Int,
    @ColumnInfo(name="first_name") val firstName: String,
    @ColumnInfo(name="last_name") val lastName: String,
    @ColumnInfo(name="pulse_reading") val pulseReading: Float,
    @ColumnInfo(name="lastUpdate") val lastUpdate: Date
) {
    val fullName: String?
        get() = "$firstName $lastName"

    val formattedPulseReading: String
        get() = String.format("%.1fbps", pulseReading)

    val formattedLastUpdate: String
        get() = SimpleDateFormat("h:mm:ss aaa", Locale.getDefault()).format(lastUpdate)
}
