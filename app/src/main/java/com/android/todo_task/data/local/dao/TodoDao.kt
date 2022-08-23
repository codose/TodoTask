package com.android.todo_task.data.local.dao

import androidx.room.*
import com.android.todo_task.data.local.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todoEntity: TodoEntity)

    @Query("SELECT * FROM todo_entity")
    fun getTodos(): Flow<List<TodoEntity>>

    @Delete
    suspend fun deleteTodo(todoEntity: TodoEntity)

    @Update
    suspend fun updateTodo(todoEntity: TodoEntity)
}
