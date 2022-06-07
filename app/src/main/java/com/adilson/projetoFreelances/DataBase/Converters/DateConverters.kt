package com.adilson.projetoFreelances.DataBase.Converters


import androidx.room.TypeConverter
import java.sql.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: java.util.Date?): Long? {
        return date?.time?.toLong()
    }
}