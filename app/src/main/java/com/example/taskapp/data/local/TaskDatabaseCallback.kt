package com.example.taskapp.data.local

import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.taskapp.app.constant.Constant.Companion.TASK_TITLE1
import com.example.taskapp.app.constant.Constant.Companion.TASK_TITLE2
import com.example.taskapp.app.constant.Constant.Companion.TASK_TITLE3
import com.example.taskapp.app.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskDatabaseCallback @Inject constructor(
    private val taskDao: TaskDao,
    @ApplicationScope private val scope: CoroutineScope
) : Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        scope.launch {
            taskDao.insertTask(TaskEntity(title = TASK_TITLE1, isDone = false))
            taskDao.insertTask(TaskEntity(title = TASK_TITLE2, isDone = false))
            taskDao.insertTask(TaskEntity(title = TASK_TITLE3, isDone = false))
        }
    }
}