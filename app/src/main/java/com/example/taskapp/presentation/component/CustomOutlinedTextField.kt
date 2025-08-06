import Dimension.Ten
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskapp.ui.theme.TaskCardColor

@Composable
fun CustomOutlinedTextField(
        newTitle: String,
        onValueChange: (String) -> Unit
    ) {
    OutlinedTextField(
        value = newTitle,
        onValueChange = { value ->
            onValueChange(value)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Ten),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = TaskCardColor
        )
    )
}