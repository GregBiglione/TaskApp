import com.example.taskapp.app.constant.Constant.Companion.TASK_TITLE1
import com.example.taskapp.app.constant.Constant.Companion.TASK_TITLE2
import com.example.taskapp.app.constant.Constant.Companion.TASK_TITLE3
import com.example.taskapp.data.local.TaskDatabase
import com.example.taskapp.data.local.TaskEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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