package com.jasonjerome.pulsarapatientsync.dataModels

import java.text.SimpleDateFormat
import java.util.*

data class Patient (
    val id: Int,
    val firstName: String,
    val lastName: String,
    val pulse: Pulse
) {
    val fullName: String?
        get() = "$firstName $lastName"
}

data class Pulse (
    val reading: Float,
    val lastUpdate: Date
) {
    val formattedPulseReading: String
        get() = String.format("%.1fbps", reading)

    val formattedLastUpdate: String
        get() = SimpleDateFormat("h:mm:ss aaa", Locale.getDefault()).format(lastUpdate)
}