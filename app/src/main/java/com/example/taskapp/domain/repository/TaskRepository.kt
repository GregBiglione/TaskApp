package com.example.taskapp.domain.repository

import com.example.taskapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
}