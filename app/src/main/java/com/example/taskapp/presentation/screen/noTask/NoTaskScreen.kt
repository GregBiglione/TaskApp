import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontStyle
import com.example.taskapp.app.constant.Constant.Companion.NO_TASK
import com.example.taskapp.ui.theme.DarkGrey

@Composable
fun NoTaskScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = NO_TASK,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.titleLarge,
            color = DarkGrey
        )
    }
}