package com.android.todo_task.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.todo_task.domain.TodoModel
import com.android.todo_task.domain.usecase.*
import com.android.todo_task.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val insertTodoUseCase: InsertTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val todos = MutableStateFlow<List<TodoModel>>(listOf())

    init {
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch(dispatchers.io) {
            getTodosUseCase.execute().collect {
                todos.value = it
            }
        }
    }
}
