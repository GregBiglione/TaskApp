package com.example.taskapp.presentation.component

import CustomCenterAlignedTopAppBar
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
import androidx.navigation.NavHostController
import com.example.taskapp.app.constant.Constant.Companion.APP_NAME
import com.example.taskapp.app.constant.Constant.Companion.UPDATE_TASK_SCREEN_TITLE
import com.example.taskapp.presentation.navigation.Routes
import com.example.taskapp.presentation.viewmodel.TaskViewModel
import goBack
import goToEditTaskScreen

@Composable
fun TaskScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    navController: NavHostController,
    ) {
    val taskList by viewModel.taskList.collectAsState()

    Scaffold(
        topBar = {
            CustomCenterAlignedTopAppBar(
                title = APP_NAME,
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(taskList) { task ->
                TaskCard(
                    task.title,
                    task.isDone,
                    modifier = Modifier.padding(0.dp),
                    onEditClick = {
                        goToEditTaskScreen(navController, task.title)
                    },
                )
            }
        }
    }
}