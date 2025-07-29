package com.example.taskapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.domain.model.Task
import com.example.taskapp.domain.usecase.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(taskUseCase: TaskUseCase) : ViewModel() {
    //----------------------------------------------------------------------------------------------
    // Get all tasks
    //----------------------------------------------------------------------------------------------

    val taskList: StateFlow<List<Task>> = taskUseCase.getAllTasksUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}