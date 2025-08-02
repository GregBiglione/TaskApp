package com.example.taskapp.app.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskapp.data.local.TaskDao
import com.example.taskapp.data.local.TaskDatabase
import com.example.taskapp.data.local.TaskDatabaseCallback
import com.example.taskapp.data.local.TaskRepositoryImplementer
import com.example.taskapp.domain.repository.TaskRepository
import com.example.taskapp.domain.usecase.GetAllTasksUseCase
import com.example.taskapp.domain.usecase.InsertTaskUseCase
import com.example.taskapp.domain.usecase.TaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //----------------------------------------------------------------------------------------------
    // Unique coroutine
    //----------------------------------------------------------------------------------------------

    @Provides
    @Singleton
    @ApplicationScope
    fun provideApplicationScope() : CoroutineScope = CoroutineScope(SupervisorJob())

    //----------------------------------------------------------------------------------------------
    // Database
    //----------------------------------------------------------------------------------------------

    @Provides
    @Singleton
    fun provideTaskDatabase(
        @ApplicationContext context: Context,
        taskDao: TaskDao,
        @ApplicationScope scope: CoroutineScope
    ): TaskDatabase {
            return Room.databaseBuilder(
                context,
                TaskDatabase::class.java,
                "task_db"
            )
                .addCallback(TaskDatabaseCallback(taskDao, scope))
                .fallbackToDestructiveMigration()
                .build()
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
        insertTaskUseCase = InsertTaskUseCase(taskRepository)
    )
}