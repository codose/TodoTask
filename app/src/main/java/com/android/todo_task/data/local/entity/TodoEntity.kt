package com.android.todo_task.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.todo_task.domain.ColorMap
import com.android.todo_task.domain.TaskStatus
import org.joda.time.DateTime

@Entity(tableName = "todo_entity")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val description: String,
    val validFrom: DateTime,
    val validTo: DateTime,
    val colorMap: ColorMap,
    val status: TaskStatus,
    val createdAt: DateTime = DateTime.now(),
    val updatedAt: DateTime = DateTime.now()
)
