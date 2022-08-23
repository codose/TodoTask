package com.android.todo_task.di

import com.android.todo_task.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTodoLocalRepository(todoLocalRepositoryImpl: TodoLocalRepositoryImpl): TodoLocalRepository
}
