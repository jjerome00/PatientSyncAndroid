package com.jasonjerome.pulsarapatientsync

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.jasonjerome.pulsarapatientsync.dataModels.Patient
import com.jasonjerome.pulsarapatientsync.patients.db.PatientListDAO
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}

@Database(entities = [(Patient::class)], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun patientListDao(): PatientListDAO
}