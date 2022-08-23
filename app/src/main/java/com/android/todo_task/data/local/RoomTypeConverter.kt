package com.android.todo_task.data.local

import androidx.room.TypeConverter
import org.joda.time.DateTime

class RoomTypeConverter {
    @TypeConverter
    fun toDateTime(value: String): DateTime = DateTime.parse(value)

    @TypeConverter
    fun fromDateTime(value: DateTime?): String = value.toString()
}
