import Dimension.Medium
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.taskapp.app.constant.Constant.Companion.UPDATE_TASK_SCREEN_BUTTON_TITLE
import com.example.taskapp.app.constant.Constant.Companion.UPDATE_TASK_SCREEN_TITLE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTaskScreen(
    title: String = "",
    navController: NavController,
) {
    var newTile by remember {
        mutableStateOf(title)
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
            )
            // Spacer ------------------------------------------------------------------------------
            CustomSpacer()
            // Button ------------------------------------------------------------------------------
            CustomButton(
                UPDATE_TASK_SCREEN_BUTTON_TITLE,
                onGoBackClick = {
                    goBack(navController)
                }
            )
        }
    }
}