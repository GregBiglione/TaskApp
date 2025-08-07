import Dimension.Medium
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskapp.app.constant.Constant.Companion.UPDATE_TASK_SCREEN_BUTTON_TITLE
import com.example.taskapp.app.constant.Constant.Companion.UPDATE_TASK_SCREEN_TITLE
import com.example.taskapp.presentation.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTaskScreen(
    title: String = "",
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel()
) {
    var newTile by remember {
        mutableStateOf(title)
    }

    val taskList by viewModel.taskList.collectAsState()
    val task = taskList.find {
        it.title == title
    }

    Scaffold(
        topBar = {
            CustomCenterAlignedTopAppBar(
                title = UPDATE_TASK_SCREEN_TITLE,
                onGoBackClick = {
                    goBack(navController)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(Medium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Text field --------------------------------------------------------------------------
            CustomOutlinedTextField(
                newTitle = newTile,
                onValueChange = {
                    newTile = it
                },
                {
                    viewModel.updateTask(task, newTile)
                    goBack(navController)
                }
            )
            // Spacer ------------------------------------------------------------------------------
            CustomSpacer()
            // Button ------------------------------------------------------------------------------
            CustomButton(
                UPDATE_TASK_SCREEN_BUTTON_TITLE,
                onClick = {
                    if(newTile != task?.title && newTile.isNotBlank()) {
                        viewModel.updateTask(task, newTile)
                        goBack(navController)
                    }
                    else {
                        Log.e("Title not changed", "❌ Title is the same or empty")
                    }
                },
            )
        }
    }
}