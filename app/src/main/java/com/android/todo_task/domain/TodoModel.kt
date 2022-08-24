package com.android.todo_task.domain

import org.joda.time.DateTime

data class TodoModel(
    val id: Long = 0L,
    val title: String,
    val description: String,
    val colorMap: Int,
    val status: TaskStatus,
    val validFrom: DateTime,
    val validTo: DateTime,
    val createdAt: DateTime,
    val updatedAt: DateTime
)

enum class TaskStatus {
    Completed,
    Active
}
