package com.android.todo_task.di

import com.android.todo_task.utils.DefaultDispatcherProvider
import com.android.todo_task.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class DispatcherModule {
    open fun getDispatchers(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideCoroutineDispatcher(): DispatcherProvider {
        return getDispatchers()
    }
}
