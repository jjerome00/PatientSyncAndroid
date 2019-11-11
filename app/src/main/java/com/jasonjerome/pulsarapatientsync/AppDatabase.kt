package com.jasonjerome.pulsarapatientsync

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.jasonjerome.pulsarapatientsync.dataModels.Patient
import com.jasonjerome.pulsarapatientsync.patients.db.PatientListDAO
import java.text.SimpleDateFormat
import java.util.*

class Converters {
    companion object {
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    }

    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): String? {
        return SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(date)
    }
}

@Database(entities = [(Patient::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun patientListDao(): PatientListDAO
}