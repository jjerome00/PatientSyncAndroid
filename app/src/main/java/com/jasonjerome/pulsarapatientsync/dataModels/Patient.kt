package com.jasonjerome.pulsarapatientsync.dataModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class PatientsResponse (
    val message: String,
    val data: List<Patient>
)

@Entity(tableName = "patient_list")
data class Patient(
    @ColumnInfo(name = "id")
    @PrimaryKey val id: Int,

    @SerializedName("first_name")
    @ColumnInfo(name = "first_name")
    val firstName: String,

    @SerializedName("last_name")
    @ColumnInfo(name = "last_name")
    val lastName: String,

    @SerializedName("pulse_reading")
    @ColumnInfo(name = "pulse_reading")
    val pulseReading: Float,

    @ColumnInfo(name = "lastUpdate")
    val lastUpdate: Date
) {
    val fullName: String?
        get() = "$firstName $lastName"

    val formattedPulseReading: String
        get() = String.format("%.1fbps", pulseReading)

    val formattedLastUpdate: String
        get() = SimpleDateFormat("h:mm:ss aaa", Locale.getDefault()).format(lastUpdate)
}
