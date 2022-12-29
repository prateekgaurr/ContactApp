package com.prateek.smsdemo.data

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

class Convertors {

    @TypeConverter
    fun dateToString(date: Date) : String = date.toString()

    @TypeConverter
    fun stringToDate(dateString: String) : Date = Date(dateString)
}