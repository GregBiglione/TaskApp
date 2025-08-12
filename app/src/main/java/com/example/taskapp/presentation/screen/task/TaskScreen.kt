package com.example.taskapp.presentation.screen.task

import CustomCenterAlignedTopAppBar
import Dimension.ExtraSmall
import Dimension.Small
import NoTaskScreen
import SwipeToDismissCard
import TaskCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.taskapp.app.constant.Constant.Companion.APP_NAME
import com.example.taskapp.presentation.viewmodel.TaskViewModel
import com.example.taskapp.ui.theme.DarkTaskColor
import com.example.taskapp.ui.theme.White
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
                containerColor = DarkTaskColor,
                contentColor = White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                )
            }
        }
    ) { innerPadding ->
        if (taskList.isNotEmpty()){
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(
                    top = Small
                ),
                verticalArrangement = Arrangement.spacedBy(
                    ExtraSmall
                )
            ) {
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
                            }
                        )
                    }
                }
            }
        }
        else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                NoTaskScreen()
            }
        }
    }
}