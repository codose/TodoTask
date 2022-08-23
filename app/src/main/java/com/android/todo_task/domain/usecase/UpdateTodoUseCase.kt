package com.android.todo_task.domain.usecase

import com.android.todo_task.data.repository.TodoLocalRepository
import com.android.todo_task.domain.TodoModel
import com.android.todo_task.domain.mapper.TodoEntityMapper
import org.joda.time.DateTime
import javax.inject.Inject

class UpdateTodoUseCase @Inject constructor(
    private val todoLocalRepository: TodoLocalRepository,
    private val todoMapper: TodoEntityMapper
) {
    suspend fun execute(todoModel: TodoModel) = todoLocalRepository.updateTodo(
        todoMapper.toEntity(todoModel).copy(updatedAt = DateTime.now())
    )
}
