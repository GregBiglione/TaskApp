import Dimension.Ten
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.example.taskapp.ui.theme.TaskCardColor

@Composable
fun CustomOutlinedTextField(
        title: String = "",
        onValueChange: (String) -> Unit,
        onDone: () -> Unit,
        modifier: Modifier = Modifier
    ) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = title,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Ten),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = TaskCardColor
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                // Close keyboard ------------------------------------------------------------------
                keyboardController?.hide()
                onDone()
            }
        )
    )
}