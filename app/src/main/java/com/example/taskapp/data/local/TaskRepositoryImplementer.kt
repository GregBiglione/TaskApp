package com.example.taskapp.data.local

import com.example.taskapp.domain.model.Task
import com.example.taskapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImplementer(private val dao: TaskDao): TaskRepository {
    override fun getAllTasks(): Flow<List<Task>> =
        dao.getAllTasks().map {
            entities -> entities.map {
                it.toTask()
            }
        }

    // ---------------------------------------------------------------------------------------------
    // Insert Task
    // ---------------------------------------------------------------------------------------------

    override suspend fun insertTask(task: Task) {
        dao.insertTask(TaskEntity.from(task))
    }

    // ---------------------------------------------------------------------------------------------
    // Update Task
    // ---------------------------------------------------------------------------------------------


    override suspend fun updateTask(task: Task) {
        dao.updateTask(TaskEntity.from(task))
    }
}