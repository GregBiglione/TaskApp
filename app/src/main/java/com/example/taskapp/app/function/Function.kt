import androidx.compose.material3.SnackbarHostState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavHostController
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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


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
            successMessage: String, navController: NavController, errorMessage: String) {
    coroutineScope.launch{
        if(text.isNotBlank() && text.isNotEmpty()){
            viewmodel.insertTask(text)
            snackbarHostState.showSnackbar(successMessage)
            goBack(navController)
        }
        else {
            snackbarHostState.showSnackbar(errorMessage)
        }
    }
}

//--------------------------------------------------------------------------------------------------
// Update task
//--------------------------------------------------------------------------------------------------

fun updateTask(coroutineScope: CoroutineScope, newTitle: String, task: Task?,
               viewmodel: TaskViewModel, snackbarHostState: SnackbarHostState,
               successMessage: String, navController: NavController, errorMessage: String) {
    coroutineScope.launch{
        if(newTitle != task?.title && newTitle.isNotBlank()){
            viewmodel.updateTask(task, newTitle)
            snackbarHostState.showSnackbar(successMessage)
            goBack(navController)
        }
        else {
            snackbarHostState.showSnackbar(errorMessage)
        }
    }
}