package com.android.todo_task.domain.usecase

import com.android.todo_task.data.repository.TodoLocalRepository
import com.android.todo_task.domain.ColorMap
import com.android.todo_task.domain.TaskStatus
import com.android.todo_task.domain.TodoModel
import com.android.todo_task.domain.mapper.TodoEntityMapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test

class DeleteTodoUseCaseTest {

    @MockK
    private lateinit var repository: TodoLocalRepository

    private lateinit var deleteTodoUseCase: DeleteTodoUseCase


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        deleteTodoUseCase = DeleteTodoUseCase(repository, TodoEntityMapper())
    }

    @Test
    fun `test that delete repository is called`() = runTest {
        coEvery {
            repository.deleteTodo(any())
        } returns Unit

        deleteTodoUseCase.execute(TodoModel(
            id = 0,
            title = "Hello",
            description = "Here",
            colorMap = ColorMap.Green,
            status = TaskStatus.Active,
            validFrom = DateTime(),
            validTo = DateTime(),
            createdAt = DateTime(),
            updatedAt = DateTime()
        ))

        coVerify {
            repository.deleteTodo(any())
        }
    }
}