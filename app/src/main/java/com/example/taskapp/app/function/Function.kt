import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.taskapp.app.constant.Constant.Companion.ERROR_MESSAGE_1
import com.example.taskapp.app.constant.Constant.Companion.ERROR_MESSAGE_2
import com.example.taskapp.app.constant.Constant.Companion.TASK_TITLE1
import com.example.taskapp.app.constant.Constant.Companion.TASK_TITLE2
import com.example.taskapp.app.constant.Constant.Companion.TASK_TITLE3
import com.example.taskapp.data.local.TaskDatabase
import com.example.taskapp.data.local.TaskEntity
import com.example.taskapp.domain.model.Task
import com.example.taskapp.presentation.navigation.Routes
import com.example.taskapp.presentation.viewmodel.TaskViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.max


//--------------------------------------------------------------------------------------------------
// Prepopulate db
//--------------------------------------------------------------------------------------------------

fun prepopulateDatabase(database: TaskDatabase) {
    CoroutineScope(Dispatchers.IO).launch {
        val dao = database.taskDao()

        dao.insertTask(TaskEntity(title = TASK_TITLE1, isDone = false))
        dao.insertTask(TaskEntity(title = TASK_TITLE2, isDone = false))
        dao.insertTask(TaskEntity(title = TASK_TITLE3, isDone = false))
    }
}

//--------------------------------------------------------------------------------------------------
// Navigation
//--------------------------------------------------------------------------------------------------

fun goBack(navController: NavController) {
    navController.popBackStack()
}

fun goToEditTaskScreen(navController: NavHostController, title: String) {
    navController.navigate(Routes.updateTaskRouteWithTitle(title))
}

fun goToAddTaskScreen(navController: NavController) {
    navController.navigate(Routes.addTaskRoute())
}

//--------------------------------------------------------------------------------------------------
// Add task
//--------------------------------------------------------------------------------------------------

fun addTask(coroutineScope: CoroutineScope, text: String,
            viewmodel: TaskViewModel, snackbarHostState: SnackbarHostState,
            successMessage: String, navController: NavController,
            errorMessage: String, onStart: () -> Unit, onComplete: () -> Unit) {
    coroutineScope.launch{
        try {
            if(text.isNotBlank() && text.isNotEmpty()){
                onStart()
                viewmodel.insertTask(text)
                snackbarHostState.showSnackbar(successMessage)
                goBack(navController)
            }
            else {
                snackbarHostState.showSnackbar(errorMessage)
            }
        }
        catch (e: Exception) {
            snackbarHostState.showSnackbar(ERROR_MESSAGE_1 +e.message + ERROR_MESSAGE_2)
        }
        finally {
            onComplete()
        }
    }
}

//--------------------------------------------------------------------------------------------------
// Update task
//--------------------------------------------------------------------------------------------------

fun updateTask(coroutineScope: CoroutineScope, newTitle: String, task: Task?,
               viewmodel: TaskViewModel, snackbarHostState: SnackbarHostState,
               successMessage: String, navController: NavController,
               errorMessage: String, onStart: () -> Unit, onComplete: () -> Unit) {
    coroutineScope.launch{
        try {
            if(newTitle != task?.title && newTitle.isNotBlank()){
                onStart()
                viewmodel.updateTask(task, newTitle)
                snackbarHostState.showSnackbar(successMessage)
                goBack(navController)
            }
            else {
                snackbarHostState.showSnackbar(errorMessage)
            }
        }
        catch (e: Exception) {
            snackbarHostState.showSnackbar(ERROR_MESSAGE_1 +e.message + ERROR_MESSAGE_2)
        }
        finally {
            onComplete()
        }
    }
}

//--------------------------------------------------------------------------------------------------
// Darker a color
//--------------------------------------------------------------------------------------------------

fun Color.darker(factor: Float = 0.6f): Color {
    return Color(
        red = max(0f, red * factor),
        green = max(0f, green * factor),
        blue = max(0f, blue * factor),
        alpha = alpha
    )
}