package com.android.todo_task.di

import android.content.Context
import androidx.room.Room
import com.android.todo_task.data.local.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(context, TodoDatabase::class.java, "todo_database").build()
    }

    @Provides
    @Singleton
    fun providesDao(todoDatabase: TodoDatabase) = todoDatabase.todoDao()
}
