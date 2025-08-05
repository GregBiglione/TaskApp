package com.example.taskapp.presentation.component

import TaskCard
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskapp.presentation.viewmodel.TaskViewModel

@Composable
fun TaskScreen(viewModel: TaskViewModel = hiltViewModel()) {
    val taskList by viewModel.taskList.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(taskList) { task ->
                TaskCard(
                    task.title,
                    task.isDone,
                    modifier = Modifier.padding(0.dp)
                )
            }
        }
    }
}