package com.example.taskapp.presentation.navigation

object Routes {
    const val TASK_LIST = "taskList"
    const val UPDATE_TASK = "updateTask/{title}"

    fun updateTaskRouteWithTitle(title: String): String {
        return "updateTask/$title"
    }
}