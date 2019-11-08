package com.jasonjerome.pulsarapatientsync.dataModels

import java.util.*

data class Patient (
    val id: Int,
    val firstName: String,
    val lastName: String,
    val pulse: Pulse
)

data class Pulse (
    val reading: Float,
    val lastUpdate: Date
)