package com.example.taskapp.presentation.component

import CustomCenterAlignedTopAppBar
import SwipeToDismissCard
import TaskCard
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.example.taskapp.ui.theme.TaskCardColor
import com.example.taskapp.ui.theme.White
import goBack
import goToAddTaskScreen
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    goToAddTaskScreen(navController)
                },
                shape = CircleShape,
                containerColor = TaskCardColor,
                contentColor = White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(taskList) { task ->
                SwipeToDismissCard(
                    onSwipe = {
                        viewModel.removeTask(task)
                    }
                ) {
                    TaskCard(
                        task.title,
                        task.isDone,
                        modifier = Modifier.padding(0.dp),
                        onEditClick = {
                            goToEditTaskScreen(navController, task.title)
                        },
                        onRemoveClick = {
                            viewModel.removeTask(task)
                        },
                        onIsDoneClick = {
                            viewModel.updateIsDone(task)
                            Log.d("Task is done or not", "$taskList\n$task")
                        }
                    )
                }
            }
        }
    }
}