package com.example.taskapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.taskapp.app.constant.Constant.Companion.TASK_TITLE1
import com.example.taskapp.app.constant.Constant.Companion.TASK_TITLE2
import com.example.taskapp.app.constant.Constant.Companion.TASK_TITLE3
import com.example.taskapp.app.di.ApplicationScope
import com.example.taskapp.domain.model.Task
import com.example.taskapp.domain.usecase.InsertTaskUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    // Dao -----------------------------------------------------------------------------------------
    abstract fun taskDao(): TaskDao
}