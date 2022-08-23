package com.android.todo_task.domain.mapper

interface TodoMapper<E, M> {
    fun fromEntity(value: E): M
    fun toEntity(value: M): E
}
