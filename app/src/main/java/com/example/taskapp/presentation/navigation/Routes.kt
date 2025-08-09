package com.example.taskapp.presentation.navigation

object Routes {
    const val TASK_LIST = "taskList"
    const val UPDATE_TASK = "updateTask/{title}"
    const val ADD_TASK = "addTask"

    fun updateTaskRouteWithTitle(title: String): String {
        return "updateTask/$title"
    }

    fun addTaskRoute(): String {
        return "addTask"
    }
}