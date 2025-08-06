import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskapp.app.constant.Constant.Companion.TITLE
import com.example.taskapp.presentation.component.TaskScreen
import com.example.taskapp.presentation.navigation.Routes

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.TASK_LIST,
    ) {
        // Start screen ----------------------------------------------------------------------------
        composable(Routes.TASK_LIST) {
            TaskScreen(
                navController = navController
            )
        }
        // End screen ------------------------------------------------------------------------------
        composable(Routes.UPDATE_TASK) { backStackEntry ->
            val title = backStackEntry.arguments?.getString(TITLE) ?: ""
            UpdateTaskScreen(
                title = title,
                navController = navController,
                //onUpdateClicked = TODO()
            )
        }
    }
}