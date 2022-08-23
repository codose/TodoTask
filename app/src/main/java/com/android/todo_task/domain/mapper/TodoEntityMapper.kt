package com.android.todo_task.domain.mapper

import com.android.todo_task.data.local.entity.TodoEntity
import com.android.todo_task.domain.TodoModel
import javax.inject.Inject

class TodoEntityMapper @Inject constructor() : TodoMapper<TodoEntity, TodoModel> {
    override fun fromEntity(value: TodoEntity): TodoModel = TodoModel(
        id = value.id,
        title = value.title,
        description = value.description,
        validFrom = value.validFrom,
        validTo = value.validTo,
        createdAt = value.createdAt,
        updatedAt = value.updatedAt
    )

    override fun toEntity(value: TodoModel): TodoEntity = TodoEntity(
        id = value.id,
        title = value.title,
        description = value.description,
        validFrom = value.validFrom,
        validTo = value.validTo,
        createdAt = value.createdAt,
        updatedAt = value.updatedAt
    )
}
