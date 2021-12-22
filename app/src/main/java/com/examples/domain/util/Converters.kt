package com.examples.domain.util

import androidx.room.TypeConverter
import java.util.*

object Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
/*
    @TypeConverter
    fun fromTimestamp(timeStamp: Long?): String? {
        return timeStamp?.let { FORMATTER.format(timeStamp) }
    }

    @TypeConverter
    fun dateToTimestamp(timeStamp: String?): Long? {
        return timeStamp?.let { FORMATTER.parse(it)?.time }
    }

    companion object{
        val FORMATTER = SimpleDateFormat("yyy-MM-dd")
    }
*/
}