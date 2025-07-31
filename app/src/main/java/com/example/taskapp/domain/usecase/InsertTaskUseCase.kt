package com.example.taskapp.domain.usecase

import com.example.taskapp.domain.model.Task
import com.example.taskapp.domain.repository.TaskRepository

class InsertTaskUseCase(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task: Task) {
        taskRepository.insertTask(task)
    }
}