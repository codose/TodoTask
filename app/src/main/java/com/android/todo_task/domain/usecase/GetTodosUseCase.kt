package com.android.todo_task.domain.usecase

import com.android.todo_task.data.repository.TodoLocalRepository
import com.android.todo_task.domain.TodoModel
import com.android.todo_task.domain.mapper.TodoEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(
    private val todoLocalRepository: TodoLocalRepository,
    private val todoMapper: TodoEntityMapper
) {
    fun execute(): Flow<List<TodoModel>> = todoLocalRepository.getTodos().map {
        it.map { entity ->
            todoMapper.fromEntity(entity)
        }
    }
}
