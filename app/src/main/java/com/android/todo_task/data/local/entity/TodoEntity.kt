package com.android.todo_task.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime

@Entity(tableName = "todo_entity")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val description: String,
    val validFrom: DateTime,
    val validTo: DateTime,
    val createdAt: DateTime = DateTime.now(),
    val updatedAt: DateTime = DateTime.now()
)
