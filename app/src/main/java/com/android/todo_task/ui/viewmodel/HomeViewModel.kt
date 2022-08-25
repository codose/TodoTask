package com.android.todo_task.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.todo_task.domain.ColorMap
import com.android.todo_task.domain.TaskStatus
import com.android.todo_task.domain.TodoModel
import com.android.todo_task.domain.usecase.*
import com.android.todo_task.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val insertTodoUseCase: InsertTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _todos = MutableStateFlow<List<TodoModel>>(listOf())
    val todos = _todos.asStateFlow()
    private val _todoModelToUpdate = MutableStateFlow<TodoModel?>(null)
    val todoModelToUpdate = _todoModelToUpdate.asStateFlow()

    init {
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch(dispatchers.io) {
            getTodosUseCase.execute().collect {
                _todos.value = it
            }
        }
    }

    fun insertTask(title: String, description: String, colorMap: ColorMap) = viewModelScope.launch {
        insertTodoUseCase.execute(
            TodoModel(
                id = 0,
                title = title,
                description = description,
                colorMap = colorMap,
                status = TaskStatus.Active,
                validFrom = DateTime.now(),
                validTo = DateTime.now().plusDays(5),
                createdAt = DateTime.now(),
                updatedAt = DateTime.now()
            )
        )
    }

    fun deleteTodo(todoModel: TodoModel) = viewModelScope.launch {
        deleteTodoUseCase.execute(todoModel)
    }

    fun updateTodo(todoModel: TodoModel) {
        _todoModelToUpdate.value = todoModel
    }

    fun markAsCompleted(todoModel: TodoModel) = viewModelScope.launch {
        updateTodoUseCase.execute(todoModel.copy(status = TaskStatus.Active))
    }
}
