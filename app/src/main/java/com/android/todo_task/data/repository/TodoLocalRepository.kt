package com.android.todo_task.data.repository

import com.android.todo_task.data.local.dao.TodoDao
import com.android.todo_task.data.local.entity.TodoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TodoLocalRepository {
    fun getTodos(): Flow<List<TodoEntity>>
    suspend fun insertTodo(todoEntity: TodoEntity)
    suspend fun deleteTodo(todoEntity: TodoEntity)
    suspend fun updateTodo(todoEntity: TodoEntity)
}

class TodoLocalRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoLocalRepository {
    override fun getTodos(): Flow<List<TodoEntity>> = todoDao.getTodos()

    override suspend fun insertTodo(todoEntity: TodoEntity) = todoDao.insertTodo(todoEntity)

    override suspend fun deleteTodo(todoEntity: TodoEntity) = todoDao.deleteTodo(todoEntity)

    override suspend fun updateTodo(todoEntity: TodoEntity) = todoDao.updateTodo(todoEntity)
}
