import Dimension.Medium
import Dimension.Small
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskapp.app.constant.Constant.Companion.ADD_TASK_SCREEN_TITLE
import com.example.taskapp.app.constant.Constant.Companion.SUCCESS_TOAST_MESSAGE
import com.example.taskapp.presentation.viewmodel.TaskViewModel
import com.example.taskapp.ui.theme.ErrorColor
import com.example.taskapp.ui.theme.SuccessColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CustomCenterAlignedTopAppBar(
                title = ADD_TASK_SCREEN_TITLE,
                onGoBackClick = {
                    goBack(navController)
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState) { snackbarData ->
                val isSuccess = snackbarData.visuals.message.contains(
                    SUCCESS_TOAST_MESSAGE,
                    ignoreCase = true
                )

                CustomSnackBar(
                    message = snackbarData.visuals.message,
                    backgroundColor = if (isSuccess) SuccessColor else ErrorColor,
                    imageVector = if (isSuccess) Icons.Filled.CheckCircle else Icons.Filled.Close,
                    modifier = Modifier.padding(Small)
                )
            }
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
            // Spacer ------------------------------------------------------------------------------
            CustomSpacer()
            // Button ------------------------------------------------------------------------------
        }
    }
}