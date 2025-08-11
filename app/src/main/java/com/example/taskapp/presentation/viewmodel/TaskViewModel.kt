package com.example.taskapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.model.Task
import com.example.taskapp.domain.usecase.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val taskUseCase: TaskUseCase) : ViewModel() {
    //----------------------------------------------------------------------------------------------
    // Get all tasks
    //----------------------------------------------------------------------------------------------

    val taskList: StateFlow<List<Task>> = taskUseCase.getAllTasksUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    //----------------------------------------------------------------------------------------------
    // Update task
    //----------------------------------------------------------------------------------------------

    fun updateTask(task: Task?, newTitle: String) {
        task?.let {
            val updatedTask = it.copy(
                title = newTitle.trim()
            )

            viewModelScope.launch {
                taskUseCase.updateTaskUseCase(updatedTask)
            }
        }
    }

    //----------------------------------------------------------------------------------------------
    // Update is done
    //----------------------------------------------------------------------------------------------

    fun updateIsDone(task: Task?) {
        task?.let {
            val updatedTask = it.copy(
                isDone = !it.isDone
            )

            viewModelScope.launch {
                taskUseCase.updateTaskUseCase(updatedTask)
            }
        }
    }

    //----------------------------------------------------------------------------------------------
    // Insert task
    //----------------------------------------------------------------------------------------------

    fun insertTask(title: String) {
        val task = Task(
            id = 0,
            title = title.trim(),
            isDone = false
        )

        viewModelScope.launch {
            taskUseCase.insertTaskUseCase(task)
            Log.d("New task", "$task")
        }
    }

    //----------------------------------------------------------------------------------------------
    // Delete task
    //----------------------------------------------------------------------------------------------

    fun removeTask(task: Task) {
        viewModelScope.launch {
            taskUseCase.removeTaskUseCase(task)
        }
    }
}