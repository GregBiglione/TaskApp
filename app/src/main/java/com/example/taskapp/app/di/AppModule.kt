package com.example.taskapp.app.di

import android.content.Context
import androidx.room.Room
import com.example.taskapp.data.local.TaskDao
import com.example.taskapp.data.local.TaskDatabase
import com.example.taskapp.data.local.TaskRepositoryImplementer
import com.example.taskapp.domain.repository.TaskRepository
import com.example.taskapp.domain.usecase.GetAllTasksUseCase
import com.example.taskapp.domain.usecase.TaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //----------------------------------------------------------------------------------------------
    // Database
    //----------------------------------------------------------------------------------------------

    @Provides
    fun provideTaskDatabase(@ApplicationContext context: Context): TaskDatabase {
        return TaskDatabase.getDatabase(context, CoroutineScope(SupervisorJob()))
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
        getAllTasksUseCase = GetAllTasksUseCase(taskRepository)
    )
}