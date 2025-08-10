package com.example.taskapp.domain.usecase

data class TaskUseCase(
    val getAllTasksUseCase: GetAllTasksUseCase,
    val insertTaskUseCase: InsertTaskUseCase,
    val updateTaskUseCase: UpdateTaskUseCase,
    val removeTaskUseCase: RemoveTaskUseCase,
)
