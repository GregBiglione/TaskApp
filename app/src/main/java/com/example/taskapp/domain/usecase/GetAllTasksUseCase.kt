package com.example.taskapp.domain.usecase

import com.example.taskapp.domain.repository.TaskRepository

class GetAllTasksUseCase (private val taskRepository: TaskRepository) {
    operator fun invoke() = taskRepository.getAllTasks()
}