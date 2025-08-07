import Dimension.Ten
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import com.example.taskapp.ui.theme.TaskCardColor

@Composable
fun CustomOutlinedTextField(
        newTitle: String,
        onValueChange: (String) -> Unit,
        onClick: (() -> Unit)? = null,
        onGoBackClick: (() -> Unit)? = null,
    ) {
    var oldValue by remember { mutableStateOf(newTitle) }

    OutlinedTextField(
        value = newTitle,
        onValueChange = { value ->
            onValueChange(value)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Ten),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = TaskCardColor
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                Log.w("Val et newTitle", "oldValue: $oldValue | newTitle: $newTitle")

                if (newTitle != oldValue && newTitle.isNotBlank()) {
                    oldValue = newTitle
                    onClick?.invoke()
                    onGoBackClick?.invoke()
                } else {
                    Log.e("Title not changed", "❌ Title is the same or empty")
                }
            }
        )
    )
}