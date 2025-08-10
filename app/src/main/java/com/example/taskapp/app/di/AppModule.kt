package com.example.taskapp.app.di

import android.content.Context
import com.example.taskapp.data.local.TaskDao
import com.example.taskapp.data.local.TaskDatabase
import com.example.taskapp.data.local.TaskRepositoryImplementer
import com.example.taskapp.domain.repository.TaskRepository
import com.example.taskapp.domain.usecase.GetAllTasksUseCase
import com.example.taskapp.domain.usecase.InsertTaskUseCase
import com.example.taskapp.domain.usecase.RemoveTaskUseCase
import com.example.taskapp.domain.usecase.TaskUseCase
import com.example.taskapp.domain.usecase.UpdateTaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //----------------------------------------------------------------------------------------------
    // Database
    //----------------------------------------------------------------------------------------------

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TaskDatabase {
        return TaskDatabase.getInstance(appContext)
    }

    //----------------------------------------------------------------------------------------------
    // Dao
    //----------------------------------------------------------------------------------------------

    @Provides
    fun provideTaskDao(db: TaskDatabase): TaskDao = db.taskDao()

    //----------------------------------------------------------------------------------------------
    // Repository
    //----------------------------------------------------------------------------------------------

    @Provides
    fun provideTaskRepository(dao: TaskDao): TaskRepository = TaskRepositoryImplementer(dao)

    //----------------------------------------------------------------------------------------------
    // Use case
    //----------------------------------------------------------------------------------------------

    @Provides
    fun provideTaskUseCase(taskRepository: TaskRepository): TaskUseCase = TaskUseCase(
        getAllTasksUseCase = GetAllTasksUseCase(taskRepository),
        insertTaskUseCase = InsertTaskUseCase(taskRepository),
        updateTaskUseCase = UpdateTaskUseCase(taskRepository),
        removeTaskUseCase = RemoveTaskUseCase(taskRepository)
    )
}